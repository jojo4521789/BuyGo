package web.back_end.lpa.order.service;

import java.util.List;

import core.service.CoreService;
import web.back_end.lpa.order.DTO.ShipOrCancelDTO;
import web.back_end.lpa.order.entity.LpaSo;
import web.back_end.lpa.order.entity.LpaSoDetails;

public interface LpaSoService extends CoreService{

	List<LpaSo> findAll(Integer memberNo); 
	
	List<LpaSo> findBuyerOrders(Integer buyerNo, Byte status);
	
	List<LpaSo> findSellerOrders(Integer sellerNo, Byte status);
	
	LpaSo findOrderDetailsByLpaSoSeq(String lpaSoSeq); 
	
	String generateNewOrder(Integer memberNo, LpaSo lpaSO, List<LpaSoDetails> lpaSoList);
	
	LpaSoDetails updateOrderDetailStatus(LpaSoDetails lpaSoDetails);
	
	int ShipOrCancelOrder (ShipOrCancelDTO shipOrCancelDTO);
	
	byte[] showFirstImage(Integer prodNo);
	
//	Lpa_SO findByOrderSeq(Lpa_SO lpa_SO);
	
//	Lpa_SO edit(Lpa_SO lpa_SO);
	
//	boolean remove(Integer orderNo);
	
//	boolean save(Lpa_SO lpa_SO);
}
