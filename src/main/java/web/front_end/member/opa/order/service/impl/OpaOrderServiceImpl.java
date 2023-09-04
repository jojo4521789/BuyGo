package web.front_end.member.opa.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import web.front_end.member.opa.order.dao.OpaOrderDao;
import web.front_end.member.opa.order.dao.impl.OpaOrderDaoImpl;
import web.front_end.member.opa.order.entity.OpaOrder;
import web.front_end.member.opa.order.service.OpaOrderService;

public class OpaOrderServiceImpl implements OpaOrderService {
	private static String [] statusMap = new String[] { "全部","待付款","待出貨","待收貨","已完成","已取消","已退款" };
	private static int [] statusMapping = new int[] { 0, 1, 2, 3, 1, 1, 3, 4, 3, 5, 6 };
	private OpaOrderDao opaOrderDao;
    
    public OpaOrderServiceImpl() {
    	opaOrderDao = new OpaOrderDaoImpl();
    }

    public List<OpaOrder> findAll() {
        return opaOrderDao.selectAll();
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

    public boolean updateStatus(int id, int status) {
        OpaOrder opaOrder = opaOrderDao.selectById(id);
        if (opaOrder == null) {
            return false;
        }
        opaOrder.setOpaSoStatus((byte) status);
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

}
