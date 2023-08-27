package web.back_end.lpa.order.service;

import java.util.List;

import core.service.CoreService;
import web.back_end.lpa.order.entity.LpaSo;
import web.back_end.lpa.order.entity.LpaSoDetails;

public interface LpaSoService extends CoreService{

	List<LpaSo> findAll(Integer memberNo); // 查詢訂單
	
	List<LpaSo> findByOrderStatus(Integer memberNo, Byte status); // 依買家編號查詢訂單
	
	LpaSo findOrderDetailsByLpaSoSeq(String lpaSoSeq); 
	
	String generateNewOrder(Integer memberNo, LpaSo lpaSO, List<LpaSoDetails> lpaSoList);
	
	LpaSoDetails updateOrderDetailStatus(LpaSoDetails lpaSoDetails);
	
//	Lpa_SO findByOrderSeq(Lpa_SO lpa_SO);
	
//	Lpa_SO edit(Lpa_SO lpa_SO);
	
//	boolean remove(Integer orderNo);
	
//	boolean save(Lpa_SO lpa_SO);
}
