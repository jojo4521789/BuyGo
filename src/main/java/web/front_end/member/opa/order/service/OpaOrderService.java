package web.front_end.member.opa.order.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.opa.order.entity.OpaOrder;


public interface OpaOrderService extends CoreService {
	public List<OpaOrder> findAll();
	
	public List<OpaOrder> [] findAllSoGroupByStatusByMember(int memberNo);
	
	public String [] getStatusMap();
	public boolean updateStatus(int id, int status);
	
	public int getPendingTransactionByMember(int memberNo);

    public List<OpaOrder> findAllOrderByStatus(int memberNo, List<Integer> status);
}