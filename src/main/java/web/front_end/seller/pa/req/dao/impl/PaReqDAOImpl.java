package web.front_end.seller.pa.req.dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import web.front_end.seller.pa.req.dao.PaReqDAO;
import web.front_end.seller.pa.req.entity.PaReq;

public class PaReqDAOImpl implements PaReqDAO{

	@Override
	public int insert(PaReq paReq) {
		getSession().persist(paReq);
		return 1;
	}
	
	@Override
	public List<PaReq> selectAll() {
	    final String hql = "FROM PA_REQUEST ORDER BY PA_RQ_PROD_NAME";
	    return getSession().createQuery(hql, PaReq.class).getResultList();
	}
	
	public PaReq selectByPaRqNo(PaReq paRqNo) {
		return getSession().get(PaReq.class, paRqNo);
	}
	
//	public int update(PaReq paReq) {
//		final StringBuilder hql = new StringBuilder().append("UPDATE PA_REQUEST SET ");
//		final Integer paRqNo = paReq.getPaRqNo();
//		if (paRqNo != null) {
//			hql.append("PA_RQ_NO = :paRqNo, ");
//		}
//		hql.append("NICKNAME = :nikename, ")
//			.append("pass = :pass, ")
//			.append("role_id = :role_id, ")
//			.append("updater = :updater, ")
//			.append("last_updated_date = NOW() ")
//			.append("WHERE username = :username");
//
//		Query<?> query = getSession().createQuery(hql.toString());
//		if (paRqNo != null) {
//			query.setParameter("paRqNo", paRqNo);
//		}
//		return query.setParameter("nikename", paReq.getNickname())
//				.setParameter("pass", paReq.getPass())
//				.setParameter("role_id", paReq.getRoleId())
//				.setParameter("updater", paReq.getUpdater())
//				.setParameter("username", paReq.getUsername())
//				.executeUpdate();
//	
//	}

	@Override
	public int deleteById(Integer id) {
		return 0;
	}
	

	public PaReq selectById(Integer id) {
		return null;
	}

	@Override
	public int update(PaReq entity) {
		// TODO Auto-generated method stub
		return 0;
	}

}
