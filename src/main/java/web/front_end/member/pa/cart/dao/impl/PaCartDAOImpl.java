package web.front_end.member.pa.cart.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.front_end.member.pa.cart.dao.PaCartDAO;
import web.front_end.member.pa.cart.entity.PaCart;
import web.front_end.member.pa.cart.entity.PaCartId;

public class PaCartDAOImpl implements PaCartDAO{

	@Override
	public int insert(PaCart paCart) {
		getSession().persist(paCart);
		return 1;
	}

	@Override
	public int deleteById(PaCartId paCartId) {
		Session session = getSession();
		PaCart paCart = session.get(PaCart.class, paCartId);
		session.remove(paCart);
		return 1;
	}

	@Override
	public int update(PaCart paCart) {
		Session session = getSession();
		session.merge(paCart);
		return 1;
	}

	@Override
	public PaCart selectById(PaCartId paCartId) {
		return getSession().get(PaCart.class, paCartId);
	}

	@Override
	public List<PaCart> selectAll() {
		
		final String hql = "FROM PaCart ORDER BY paCartId";
		return getSession().createQuery(hql, PaCart.class).getResultList();
		
	}
	
	@Override
	public List<PaCart> selectByMemberNo(Integer memberNo) {
		final String hql = "FROM PaCart WHERE paCartId.memberNo = :memberNo";
		try {
			Query<PaCart> query = getSession().createQuery(hql, PaCart.class).setParameter("memberNo", memberNo);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PaCart selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
