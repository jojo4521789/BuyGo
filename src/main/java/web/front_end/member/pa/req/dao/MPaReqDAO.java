package web.front_end.member.pa.req.dao;

import core.dao.CoreDao;
import web.front_end.member.pa.req.entity.MPaReq;

public interface MPaReqDAO extends CoreDao<MPaReq, Integer>{
	
	public MPaReq selectByMemberNoSeller(Integer memberNoSeller);
	
	public MPaReq selectByPaRqProdName(String paRqProdName);

}
