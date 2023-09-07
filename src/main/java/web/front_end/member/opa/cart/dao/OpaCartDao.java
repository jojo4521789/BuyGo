package web.front_end.member.opa.cart.dao;

import static core.util.HibernateUtil.getSessionFactory;

import java.util.List;

import org.hibernate.Session;

import web.front_end.member.opa.cart.entity.OpaCart;
import web.front_end.member.opa.cart.entity.OpaCartId;

public interface OpaCartDao {
	int insert(OpaCart opaCart);

	int deleteById(OpaCartId opaCartId);

	int update(OpaCart opaCart);

	OpaCart selectById(OpaCartId opaCartId);

	List<OpaCart> selectAll();
	
	List<OpaCart> selectByMemberNo(Integer memberNo);

	default Session getSession() {
		return getSessionFactory().getCurrentSession();
//		return getSessionFactory().openSession();
	}
}
