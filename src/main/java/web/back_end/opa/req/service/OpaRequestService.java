package web.back_end.opa.req.service;

import java.util.List;

import core.service.CoreService;
import web.back_end.opa.req.entity.OpaRequest;
import web.front_end.member.notification.entity.Notification;

public interface OpaRequestService extends CoreService {

    public Integer save(OpaRequest request);

	public List<OpaRequest> findAllRequests();
	
	public List<OpaRequest> findAllRequestsByMember(int id);
	
	public boolean updateStatus(int id, int status, Integer failed);
    
    public Notification getNotification(int id);
}
