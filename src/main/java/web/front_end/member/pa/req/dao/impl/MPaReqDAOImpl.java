package web.front_end.member.pa.req.dao.impl;

import java.util.List;

import org.hibernate.Transaction;

import web.front_end.member.pa.req.dao.MPaReqDAO;
import web.front_end.member.pa.req.entity.MPaReq;

public class MPaReqDAOImpl implements MPaReqDAO {

	//新增委託內容
	@Override
	public int insert(MPaReq mPaReq) {
		getSession().persist(mPaReq);
		return 1;
	}
	
	//用會員ID去查詢屬於該會員ID的委託單資料
	public List<MPaReq> selectByMemberNo(Integer memberNoMember) {
		final String hql = "FROM MPaReq WHERE memberNoMember = :memberNoMember ORDER BY paRqNo";
		return getSession()
				.createQuery(hql, MPaReq.class)
				.setParameter("memberNoMember", memberNoMember)
				.getResultList();
	}
	
	
	public MPaReq selectByMemberNoMember(Integer memberNoMember) {
		return getSession().get(MPaReq.class, memberNoMember);
	}

	public MPaReq selectByMemberNoSeller(Integer memberNoSeller) {
		return getSession().get(MPaReq.class, memberNoSeller);
	}
	
	public MPaReq selectByPaRqProdName(String paRqProdName) {
		return getSession().get(MPaReq.class, paRqProdName);
	}
	
	public MPaReq selectByPaRqNo(String paRqNo) {
		return getSession().get(MPaReq.class, paRqNo);
	}

	//一次查出所有資料庫中的委託內容
	@Override
	public List<MPaReq> selectAll() {
		final String hql = "FROM PA_RQ_NO";
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
