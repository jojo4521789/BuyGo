package web.back_end.opa.coupon.dao.impl;

import java.util.List;

import org.hibernate.Session;

import web.back_end.opa.coupon.dao.CpOwnerDao;
import web.back_end.opa.coupon.entity.CpOwner;
import web.back_end.opa.coupon.entity.CpOwnerId;

public class CpOwnerDaoImpl implements CpOwnerDao{

	@Override
	public int insert(CpOwner cpOwner) {
		getSession().persist(cpOwner);
		return 1;
	}

	@Override
	public int deleteById(CpOwnerId cpOwnerId) {
		Session session = getSession();
		CpOwner cpOwner = session.get(CpOwner.class, cpOwnerId);
		session.remove(cpOwner);
		return 1;
	}

	@Override
	public int update(CpOwner cpOwner) {
		Session session = getSession();
		CpOwner oldCpOwner = session.get(CpOwner.class, cpOwner.getCpOwnerId());
		final Integer opaCpownerStatus = cpOwner.getOpaCpownerStatus();
		if(opaCpownerStatus != null) {
			oldCpOwner.setOpaCpownerStatus(opaCpownerStatus);
		}
		return 1;
	}

	@Override
	public CpOwner selectById(CpOwnerId cpOwnerId) {
		return getSession().get(CpOwner.class, cpOwnerId);
	}

	@Override
	public List<CpOwner> selectAll() {
		final String hql = "FROM CpOwner ORDER BY cpOwnerId";
		return getSession().createQuery(hql, CpOwner.class).getResultList();
	}

}
