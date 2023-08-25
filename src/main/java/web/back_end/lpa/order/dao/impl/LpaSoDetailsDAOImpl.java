package web.back_end.lpa.order.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.back_end.lpa.order.dao.LpaSoDetailsDAO;
import web.back_end.lpa.order.entity.LpaSoDetails;

public class LpaSoDetailsDAOImpl implements LpaSoDetailsDAO{

	@Override
	public int insert(LpaSoDetails entity) {
		getSession().persist(entity);
		return 1;
	}

	@Override
	public int deleteById(Integer lpaSoNo) {
		Session session = getSession();
		LpaSoDetails lpaSoDetails = session.get(LpaSoDetails.class, lpaSoNo);
		session.remove(lpaSoDetails);
		return 1;
	}

	@Override
	public int update(LpaSoDetails entity) {
//		final StringBuilder hql = new StringBuilder().append("UPDATE LpaSoDetails SET ");
		return 0;
	}
	
	public int updateStatus(Integer lpaSoNo, Integer lpaProdNo, Integer status) {
		final String hql = "UPDATE LpaSoDetails SET status = :status WHERE lpaSoNo = :lpaSoNo AND lpaProdNo = :lpaProdNo";
		return getSession()
				.createQuery(hql)
				.setParameter("status", status)
				.setParameter("lpaSoNo", lpaSoNo)
				.setParameter("lpaProdNo", lpaProdNo)
				.executeUpdate();
	}

	@Override
	public LpaSoDetails selectById(Integer id) {
		return getSession().get(LpaSoDetails.class, id);
	}

	@Override
	public List<LpaSoDetails> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public LpaSoDetails selectBySoNoAndProdNo(Integer lpaSoNo, Integer lpaProdNo) {
		final String hql = "FROM LpaSoDetails WHERE lpaSoNo = :lpaSoNo AND lpaProdNo = :lpaProdNo";
		return getSession()
				.createQuery(hql, LpaSoDetails.class)
				.setParameter("lpaSoNo", lpaSoNo)
				.setParameter("lpaProdNo", lpaProdNo)
				.getSingleResult();
	}

}
