package web.front_end.member.opa.order.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.opa.order.entity.*;

import web.front_end.member.notification.entity.Notification;

public interface OpaOrderService extends CoreService {
	public List<OpaOrder> findAll();
	
	public List<OpaOrder> [] findAllSoGroupByStatusByMember(int memberNo);
	
	public String [] getStatusMap();
	public boolean updateStatus(int id, int status, Integer failed);
	
	public int getPendingTransactionByMember(int memberNo);

    public List<OpaOrder> findAllOrderByStatus(int memberNo, List<Integer> status);
    public Notification getCancelNotification(int id);

	public OpaOrder save(OpaOrder order);
	public OpaOrderdetailsId save(OpaOrderdetails orderdetails);
	
	OpaOrderdetails addOpaOrderdetails(OpaOrderdetails opaOrderdetails);
	List<OpaOrderdetails> selectOrderdetailsByOpaSoNo(Integer opaSoNo);
	OpaOrder update(OpaOrder opaOrder);
	OpaOrder selectById(Integer opaSoNo);
}