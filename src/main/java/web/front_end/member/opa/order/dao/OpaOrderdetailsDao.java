package web.front_end.member.opa.order.dao;

import static core.util.HibernateUtil.getSessionFactory;

import java.util.List;

import org.hibernate.Session;

import web.front_end.member.opa.order.entity.OpaOrderdetails;

public interface OpaOrderdetailsDao {
	int insert(OpaOrderdetails opaOrderdetails);
	
	List<OpaOrderdetails> selectByOpaSoNo(Integer opaSoNo);
	
	default Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
}
