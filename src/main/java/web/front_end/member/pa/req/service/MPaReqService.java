package web.front_end.member.pa.req.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.pa.req.entity.MPaReq;

public interface MPaReqService extends CoreService{
	
	public MPaReq add(MPaReq mPaReq);

	List<MPaReq> checkAll();

}
