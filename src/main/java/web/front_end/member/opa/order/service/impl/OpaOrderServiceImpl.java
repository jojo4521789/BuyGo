package web.front_end.member.opa.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import web.front_end.member.opa.order.dao.OpaOrderDao;
import web.front_end.member.opa.order.dao.OpaOrderdetailsDao;
import web.front_end.member.opa.order.dao.impl.OpaOrderDaoImpl;
import web.front_end.member.opa.order.dao.impl.OpaOrderdetailsDaoImpl;
import web.front_end.member.opa.order.entity.*;
import web.front_end.member.opa.order.service.OpaOrderService;
import web.front_end.member.notification.entity.Notification;

public class OpaOrderServiceImpl implements OpaOrderService {
    public static String [] failedReasonMap = new String[] { "藥品, 醫療器材", "酒類 / 菸類商品", "武器 / 彈藥 / 軍事用品", "活體動物, 保育動物及其製品", "導向外部資訊或交易", "此商品可能令人感到不適或違反善良風俗","仿冒品","重覆刊登/複製他人商品圖文","違反鑑賞期","疫區/國外肉製, 蛋, 海鮮, 寵物食品","刷單製造不實銷量","濫用文字誤導搜尋","NCC/BSMI認證字號未填寫","誇大不實療效/涉及醫療效能","其他","無庫存" };
	private static String [] statusMap = new String[] { "全部","待付款","待出貨","待收貨","已完成","已取消","已退款" };
	private static int [] statusMapping = new int[] { 0, 1, 2, 3, 1, 1, 3, 4, 3, 5, 6 };
	private OpaOrderDao opaOrderDao;
	private OpaOrderdetailsDao opaOrderdetailsDao;
    
    public OpaOrderServiceImpl() {
    	opaOrderDao = new OpaOrderDaoImpl();
    	opaOrderdetailsDao = new OpaOrderdetailsDaoImpl();
    }

    public List<OpaOrder> findAll() {
        return opaOrderDao.selectAll();
    }

	public OpaOrder findById(int id) {
        return opaOrderDao.selectById(id);
    }

    public Notification getCancelNotification(int id) throws RuntimeException {
        OpaOrder order = opaOrderDao.selectById(id);
        if(order == null)
            throw new RuntimeException("Order not found");
        Integer opaFailedReason = order.getOpaFailedReason();
        if(opaFailedReason == null)
            throw new RuntimeException("No error found");

        String message = "訂單#" + String.valueOf(id) + "已取消, 原因: " + failedReasonMap[opaFailedReason];
        Notification notification = new Notification(0);
        notification.setNotifiTitle("訂單已取消");
        notification.setNotifiContent(message);
		notification.setMemberNo(order.getMemberNo());
        notification.setNotifiTime(new Timestamp(System.currentTimeMillis()));
        return notification;
    }

    @SuppressWarnings("unchecked")
    public List<OpaOrder>[] findAllSoGroupByStatusByMember(int memberNo) {
        List<OpaOrder> results = opaOrderDao.selectAllByMember(memberNo);
        List<OpaOrder>[] OpaOrders = (List<OpaOrder>[]) new List[statusMap.length];
        for (int i = 0; i < OpaOrders.length; i++) {
            OpaOrders[i] = new ArrayList<OpaOrder>();
        }
        for (OpaOrder opaOrder : results) {
            int target = statusMapping[opaOrder.getOpaSoStatus()];
            OpaOrders[target].add(opaOrder);
            if (target > 0)
                OpaOrders[0].add(opaOrder);
        }
        return OpaOrders;
    }

    @Override
    public List<OpaOrder> findAllOrderByStatus(int memberNo, List<Integer> status) {
        ArrayList<OpaOrder> results = new ArrayList<>();
        for (int i = 0; i < status.size(); i++) {
            results.addAll(opaOrderDao.selectAllByStatus(memberNo, status.get(i)));
        }
        // order by opaSoNo desc
        results.sort((a, b) -> b.getOpaSoNo() - a.getOpaSoNo());
        return results;
    }

    public String[] getStatusMap() {
        return statusMap;
    }

    public boolean updateStatus(int id, int status, Integer failed) {
        OpaOrder opaOrder = opaOrderDao.selectById(id);
        if (opaOrder == null) {
            return false;
        }
        opaOrder.setOpaSoStatus((byte) status);
        if(failed != null)
            opaOrder.setOpaFailedReason(failed);
        opaOrderDao.update(opaOrder);
        return true;
    }

    public int getPendingTransactionByMember(int memberNo) {
        List<OpaOrder> OpaOrders = opaOrderDao.selectAllByMember(memberNo);
        int pendingTransaction = 0;
        for (OpaOrder opaOrder : OpaOrders) {
            if (opaOrder.getOpaSoStatus() < 8) {
                if (opaOrder.getOpaRealStatus() == 0) {
                    pendingTransaction += opaOrder.getOpaFirAmount() + opaOrder.getOpaSecAmount();
                } else if (opaOrder.getOpaRealStatus() == 1 || opaOrder.getOpaRealStatus() == 2)
                    pendingTransaction += opaOrder.getOpaSecAmount();
            }
        }
        return pendingTransaction;
    }

    public OpaOrder save(OpaOrder order) {
    	final int resultCount = (Integer) opaOrderDao.save(order);
    	if(resultCount < 1) {
    		order.setMessage("新增訂單錯誤，請聯絡管理員!");
    		order.setSuccessful(false);
    		return order;
    	}
    	
    	order.setMessage("新增訂單成功");
		order.setSuccessful(true);
		return order;
    }

	public OpaOrderdetailsId save(OpaOrderdetails orderdetails) {
        return (OpaOrderdetailsId) opaOrderDao.save(orderdetails);
    }

	@Override
	public OpaOrderdetails addOpaOrderdetails(OpaOrderdetails opaOrderdetails) {
		final int resultCount = opaOrderdetailsDao.insert(opaOrderdetails);
		if(resultCount < 1) {
			opaOrderdetails.setMessage("新增訂單明細失敗，請聯絡管理員!");
			opaOrderdetails.setSuccessful(false);
			return opaOrderdetails;
		}
		opaOrderdetails.setMessage("新增訂單明細成功");
		opaOrderdetails.setSuccessful(true);
		return opaOrderdetails;
	}

	@Override
	public List<OpaOrderdetails> selectOrderdetailsByOpaSoNo(Integer opaSoNo) {
		return opaOrderdetailsDao.selectByOpaSoNo(opaSoNo);
	}

	@Override
	public OpaOrder update(OpaOrder opaOrder) {
		final int resultCount = opaOrderDao.update(opaOrder);
		opaOrder.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		opaOrder.setSuccessful(resultCount > 0);
		return opaOrder;
	}

	@Override
	public OpaOrder selectById(Integer opaSoNo) {
		return opaOrderDao.selectById(opaSoNo);
	}

}
