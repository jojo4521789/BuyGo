package web.front_end.member.opa.cart.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.front_end.member.opa.cart.dao.OpaCartDao;
import web.front_end.member.opa.cart.entity.OpaCart;
import web.front_end.member.opa.cart.entity.OpaCartId;

public class OpaCartDaoImpl implements OpaCartDao{

	@Override
	public int insert(OpaCart opaCart) {
		getSession().persist(opaCart);
		return 1;
	}

	@Override
	public int deleteById(OpaCartId opaCartId) {
		Session session = getSession();
		OpaCart opaCart = session.get(OpaCart.class, opaCartId);
		session.remove(opaCart);
		return 1;
	}

	@Override
	public int update(OpaCart opaCart) {
		Session session = getSession();
		session.merge(opaCart);
		return 1;
	}

	@Override
	public OpaCart selectById(OpaCartId opaCartId) {
		return getSession().get(OpaCart.class, opaCartId);
	}

	@Override
	public List<OpaCart> selectAll() {
		final String hql = "FROM OpaCart ORDER BY opaCartId";
		return getSession().createQuery(hql, OpaCart.class).getResultList();
	}

	@Override
	public List<OpaCart> selectByMemberNo(Integer memberNo) {
		final String hql = "FROM OpaCart WHERE opaCartId.memberNo = :memberNo";
		try {
			Query<OpaCart> query = getSession().createQuery(hql, OpaCart.class).setParameter("memberNo", memberNo);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
