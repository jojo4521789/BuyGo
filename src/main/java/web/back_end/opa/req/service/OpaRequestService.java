package web.back_end.opa.req.service;

import java.util.List;

import core.service.CoreService;
import web.back_end.opa.req.entity.OpaRequest;


public interface OpaRequestService extends CoreService {

	public List<OpaRequest> findAllRequests();
	
    public boolean updateStatus(int id, int status);
    
    public boolean sendNotification(int id);
}
