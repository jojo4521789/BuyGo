package web.front_end.member.pa.req.dao.impl;

import java.util.List;

import web.front_end.member.pa.req.dao.MPaReqDAO;
import web.front_end.member.pa.req.entity.MPaReq;

public class MPaReqDAOImpl implements MPaReqDAO {

	@Override
	public int insert(MPaReq mPaReq) {
		getSession().persist(mPaReq);
		return 1;
	}

	public MPaReq selectByMemberNoSeller(Integer memberNoSeller) {
		return getSession().get(MPaReq.class, memberNoSeller);
	}
	
	public MPaReq selectByPaRqProdName(String paRqProdName) {
		return getSession().get(MPaReq.class, paRqProdName);
	}

	@Override
	public List<MPaReq> selectAll() {
		final String hql = "FROM PA_RQ_PROD_NAME";
		return getSession().createQuery(hql, MPaReq.class).getResultList();
	}

	@Override
	public MPaReq selectById(Integer id) {
		return null;
	}
	
	@Override
	public int deleteById(Integer id) {
		return 0;
	}

	@Override
	public int update(MPaReq entity) {
		return 0;
	}

}
