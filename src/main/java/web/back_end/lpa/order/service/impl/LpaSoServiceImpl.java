package web.back_end.lpa.order.service.impl;

import java.util.LinkedList;
import java.util.List;

import web.back_end.lpa.order.DTO.ShipOrCancelDTO;
import web.back_end.lpa.order.dao.LpaSoDAO;
import web.back_end.lpa.order.dao.LpaSoDetailsDAO;
import web.back_end.lpa.order.dao.impl.LpaSoDAOImpl;
import web.back_end.lpa.order.dao.impl.LpaSoDetailsDAOImpl;
import web.back_end.lpa.order.entity.LpaSo;
import web.back_end.lpa.order.entity.LpaSoDetails;
import web.back_end.lpa.order.service.LpaSoService;
import web.back_end.lpa.product.dao.LpaProdPicsDAO;
import web.back_end.lpa.product.dao.impl.LpaProdPicsDAOImpl;

public class LpaSoServiceImpl implements LpaSoService {
	private LpaSoDAO lpaSoDAO;
	private LpaSoDetailsDAO lpaSoDetailsDAO;
	private LpaProdPicsDAO lpaProdPicsDAO;
	private static final int[] DIGIT_RANGE = { 9, 99, 999, 9999, 99999, 999999, 9999999 };

	public LpaSoServiceImpl() {
		lpaSoDAO = new LpaSoDAOImpl();
		lpaSoDetailsDAO = new LpaSoDetailsDAOImpl();
		lpaProdPicsDAO = new LpaProdPicsDAOImpl();
	}
	
	// 查詢買家訂單(依訂單狀態)
	public List<LpaSo> findBuyerOrders(Integer buyerNo, Byte status) {
		return lpaSoDAO.selectByBuyerNo(buyerNo, status);
	}

	// 查詢賣家訂單(依訂單狀態)
	@Override
	public List<LpaSo> findSellerOrders(Integer sellerNo, Byte status) {
		List<LpaSo> oldList = lpaSoDAO.selectAllByStatus(status); // 先查詢全部訂單(僅依狀態分類)
		List<LpaSo> newList = new LinkedList<LpaSo>();
		for (LpaSo lpaSo : oldList) {
			for (LpaSoDetails lpaSoDetails : lpaSo.getLpaSoDetails()) {
				if(lpaSoDetails.getLpaProd().getMemberNo() == sellerNo) { // 確認商品明細是哪個賣家
					newList.add(lpaSo);
					break; // 因一張訂單都是同一個賣家, 只要加入一次List即可
				}
			}
		}
		return newList;
	}
	
	// 依訂單編號查詢訂單詳情
	public LpaSo findOrderDetailsByLpaSoSeq(String lpaSoSeq) {
		return lpaSoDAO.selectByOrderSeq(lpaSoSeq);
	}

	// 產生新訂單
	public String generateNewOrder(Integer memberNo, LpaSo lpaSo, List<LpaSoDetails> lpaSoDetailsList) {
		lpaSo.setMemberNo(memberNo); // 設定買家
		Integer generatedId = lpaSoDAO.insert(lpaSo); // 新增LpaSo並取得自增主鍵值
		lpaSoDAO.updateSoSeq(generatedId, seqPadding(generatedId)); // 更新LpaSo.lpaSeq
		for (LpaSoDetails lpaSoDetails : lpaSoDetailsList) { // 新增LpaSoDetails
			lpaSoDetails.setLpaSoNo(generatedId);
			lpaSoDetailsDAO.insert(lpaSoDetails);
		}
		
		// 加入刪除購物車欄位
		
		String lpaSoSeq = lpaSoDAO.selectById(generatedId).getLpaSoSeq(); // why null??
		return "新訂單已成立，訂單編號為: " + seqPadding(generatedId);
	}
	
	// 將新訂單產生編號
	public static String seqPadding(Integer nextId) {
		
		int digitNum = 0, count = 7;
		// 檢查自增主鍵值有幾個數字
		for (;; digitNum++) {
			if (nextId <= DIGIT_RANGE[digitNum]) {
				digitNum++;
				break;
			}
		}

		// 根據數字數量再補上對應0的數量到7個位數
		// 例如：自增主鍵值是3，所以要再補上6個0，串接成為SO0000003
		// 又或是自增主鍵值為13，所以要再補上5個0，串接成為SO0000013
		StringBuilder sb = new StringBuilder("SO");
		for (int i = 1; i <= (count - digitNum); i++) {
			sb.append(0);
		}

		return sb.append(nextId).toString();
	}

	// 更新訂單詳情每個商品的狀態(是否買到商品)
	public LpaSoDetails updateOrderDetailStatus(LpaSoDetails lpaSoDetails) {
			Integer lpaSoNo= lpaSoDetails.getLpaSoNo();
			Integer lpaProdNo = lpaSoDetails.getLpaProdNo();
			Integer status = lpaSoDetails.getStatus();
			lpaSoDetailsDAO.updateStatus(lpaSoNo, lpaProdNo, status);
				
		return lpaSoDetailsDAO.selectBySoNoAndProdNo(lpaSoNo, lpaProdNo);
	}
	
	// 取消訂單&退款至買家錢包
	public int ShipOrCancelOrder (ShipOrCancelDTO shipOrCancelDTO) {
		Integer lpaSoNo = shipOrCancelDTO.getLpaSoNo();
		Byte status = shipOrCancelDTO.getNewStatus();
		Integer buyerNo = shipOrCancelDTO.getBuyerNo();
		Integer refundAmount = Integer.valueOf(shipOrCancelDTO.getRefundToBuyer());
		// 若新狀態是3(確認收貨), 不用退款給買家
		if (status == 3) {
			lpaSoDAO.updateSoStatus(lpaSoNo, status);
		} else {
			lpaSoDAO.updateSoStatus(lpaSoNo, status);
			// 待補退款至買家錢包
		}
		
		return 0;
	}

	
	// 依商品號碼查詢第一個圖片
	public byte[] showFirstImage(Integer prodNo) {
		System.out.println("抓圖片service有運作");
		return lpaProdPicsDAO.selectProdFirstPic(prodNo);
	}
	
	@Override
	public List<LpaSo> findAll(Integer memberNo) {
		// TODO Auto-generated method stub
		return null;
	}
}
