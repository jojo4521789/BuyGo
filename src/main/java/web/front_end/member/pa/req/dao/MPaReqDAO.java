package web.front_end.member.pa.req.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.pa.req.entity.MPaReq;

public interface MPaReqDAO extends CoreDao<MPaReq, Integer>{
	
	List<MPaReq> selectByMemberNo(Integer memberNoMember);
	
	List<MPaReq> selectByMemberNoSeller(Integer memberNoSeller);
	
	int updateReqStatus(MPaReq mPaReq);

}
