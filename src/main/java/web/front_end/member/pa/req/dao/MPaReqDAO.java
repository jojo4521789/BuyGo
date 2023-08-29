package web.front_end.member.pa.req.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.pa.req.entity.MPaReq;

public interface MPaReqDAO extends CoreDao<MPaReq, Integer>{
	
	MPaReq selectByMemberNoMember(Integer memberNoMember);
	
	MPaReq selectByMemberNoSeller(Integer memberNoSeller);
	
	MPaReq selectByPaRqProdName(String paRqProdName);
	
	MPaReq selectByPaRqNo(String paRqNo);
	
	List<MPaReq> selectByMemberNo(Integer memberNoMember);

}
