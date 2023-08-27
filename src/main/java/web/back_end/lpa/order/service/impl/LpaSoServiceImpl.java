package web.back_end.lpa.order.service.impl;

import java.util.List;

import web.back_end.lpa.order.dao.LpaSoDAO;
import web.back_end.lpa.order.dao.LpaSoDetailsDAO;
import web.back_end.lpa.order.dao.impl.LpaSoDAOImpl;
import web.back_end.lpa.order.dao.impl.LpaSoDetailsDAOImpl;
import web.back_end.lpa.order.entity.LpaSo;
import web.back_end.lpa.order.entity.LpaSoDetails;
import web.back_end.lpa.order.service.LpaSoService;

public class LpaSoServiceImpl implements LpaSoService {
	private LpaSoDAO lpaSoDAO;
	private LpaSoDetailsDAO lpaSoDetailsDAO;
	private static final int[] DIGIT_RANGE = { 9, 99, 999, 9999, 99999, 999999, 9999999 };

	public LpaSoServiceImpl() {
		lpaSoDAO = new LpaSoDAOImpl();
		lpaSoDetailsDAO = new LpaSoDetailsDAOImpl();
	}
	
	public List<LpaSo> findByOrderStatus(Integer memberNo, Byte status) {
		System.out.println("以訂單狀態查詢");
		return lpaSoDAO.selectByOrderStatus(memberNo, status);
	}

	@Override
	public List<LpaSo> findAll(Integer memberNo) {
		System.out.println("執行全部查詢");
		return lpaSoDAO.selectAllByMember(memberNo);
	}
	
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
}
