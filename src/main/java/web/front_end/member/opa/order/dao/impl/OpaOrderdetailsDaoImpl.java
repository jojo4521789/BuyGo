package web.front_end.member.opa.order.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.query.Query;

import web.front_end.member.opa.order.dao.OpaOrderdetailsDao;
import web.front_end.member.opa.order.entity.OpaOrderdetails;

public class OpaOrderdetailsDaoImpl implements OpaOrderdetailsDao{

	@Override
	public int insert(OpaOrderdetails opaOrderdetails) {
		getSession().persist(opaOrderdetails);
		return 1;
	}

	@Override
	public List<OpaOrderdetails> selectByOpaSoNo(Integer opaSoNo) {
		final String hql = "FROM OpaOrderdetails WHERE id.opaSoNo = :opaSoNo";
		try {
			Query<OpaOrderdetails> query = getSession().createQuery(hql, OpaOrderdetails.class).setParameter("opaSoNo", opaSoNo);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
