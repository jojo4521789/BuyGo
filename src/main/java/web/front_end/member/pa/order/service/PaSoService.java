package web.front_end.member.pa.order.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.pa.order.DTO.ShipOrCancelDTO;
import web.front_end.member.pa.order.entity.PaReturn;
import web.front_end.member.pa.order.entity.PaSo;
import web.front_end.member.pa.order.entity.PaSoDetails;

public interface PaSoService extends CoreService{

	List<PaSo> findAll(); 
	
	List<PaSo> findBuyerOrders(Integer buyerNo, Byte status);
	
	List<PaSo> findSellerOrders(Integer sellerNo, Byte status);
	
	PaSo findPaSoByPaSoNo(Integer paSoNo); 
	
	String generateNewOrder(Integer memberNo, PaSo lpaSO, List<PaSoDetails> lpaSoList);
	
	PaSoDetails updateOrderDetailStatus(PaSoDetails paSoDetails);
	
	int ShipOrCancelOrder (ShipOrCancelDTO shipOrCancelDTO);
	
//	byte[] showFirstImage(Integer prodNo);
	String showFirstImage(Integer prodNo);
	
	String generateNewReturn(PaReturn paReturn, List<Integer> paReturnProds, List<String> images);
	
	public int updateOrCancelReturn(PaReturn paReturn);
	
//	Pa_SO findByOrderSeq(Pa_SO pa_SO);
	
//	Pa_SO edit(Pa_SO pa_SO);
	
//	boolean remove(Integer orderNo);
	
//	boolean save(Pa_SO pa_SO);
}
