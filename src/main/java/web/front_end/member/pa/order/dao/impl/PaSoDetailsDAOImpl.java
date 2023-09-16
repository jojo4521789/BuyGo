package web.front_end.member.pa.order.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.front_end.member.pa.order.dao.PaSoDetailsDAO;
import web.front_end.member.pa.order.entity.PaSoDetails;


public class PaSoDetailsDAOImpl implements PaSoDetailsDAO{

	@Override
	public int insert(PaSoDetails entity) {
		getSession().persist(entity);
		return 1;
	}

	@Override
	public int deleteById(Integer paSoNo) {
		Session session = getSession();
		PaSoDetails paSoDetails = session.get(PaSoDetails.class, paSoNo);
		session.remove(paSoDetails);
		return 1;
	}

	@Override
	public int update(PaSoDetails entity) {
//		final StringBuilder hql = new StringBuilder().append("UPDATE PaSoDetails SET ");
		return 0;
	}
	
	public int updateStatus(Integer paSoNo, Integer paProdNo, Integer status) {
		final String hql = "UPDATE PaSoDetails SET status = :status WHERE paSoNo = :paSoNo AND paProdNo = :paProdNo";
		return getSession()
				.createQuery(hql)
				.setParameter("status", status)
				.setParameter("paSoNo", paSoNo)
				.setParameter("paProdNo", paProdNo)
				.executeUpdate();
	}

	@Override
	public PaSoDetails selectById(Integer id) {
		return getSession().get(PaSoDetails.class, id);
	}

	@Override
	public List<PaSoDetails> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PaSoDetails selectBySoNoAndProdNo(Integer paSoNo, Integer paProdNo) {
		final String hql = "FROM PaSoDetails WHERE paSoNo = :paSoNo AND paProdNo = :paProdNo";
		return getSession()
				.createQuery(hql, PaSoDetails.class)
				.setParameter("paSoNo", paSoNo)
				.setParameter("paProdNo", paProdNo)
				.getSingleResult();
	}

}
