package web.back_end.opa.prod.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.back_end.opa.prod.dao.PrpicsDao;
import web.back_end.opa.prod.entity.Prpics;

public class PrpicsDaoImpl implements PrpicsDao{

	@Override
	public int insert(Prpics prpics) {
		getSession().persist(prpics);
		return 1;
	}

	@Override
	public int deleteById(Integer opaPrpicsNo) {
		Session session = getSession();
		Prpics prpics = session.get(Prpics.class, opaPrpicsNo);
		session.remove(prpics);
		return 1;
	}

	@Override
	public int update(Prpics prpics) {
		Session session = getSession();
		Prpics oldPrpics = session.get(Prpics.class, prpics.getOpaPrpicsNo());
		final Integer opaProdNo = prpics.getOpaProdNo();
		if(opaProdNo != null) {
			oldPrpics.setOpaProdNo(opaProdNo);
		}
		final byte[] opaProdPicture = prpics.getOpaProdPicture();
		if(opaProdPicture != null) {
			oldPrpics.setOpaProdPicture(opaProdPicture);
		}
		return 1;
	}

	@Override
	public Prpics selectById(Integer opaPrpicsNo) {
		return getSession().get(Prpics.class, opaPrpicsNo);
	}

	@Override
	public List<Prpics> selectAll() {
		final String hql = "FROM Prpics ORDER BY opaPrpicsNo";
		return getSession().createQuery(hql, Prpics.class).getResultList();
	}

	@Override
	public List<Prpics> SelectByOpaProdNo(Integer opaProdNo) {
		try {
			Query<Prpics> query = getSession().createQuery("FROM Prpics WHERE opaProdNo = :opaProdNo", Prpics.class).setParameter("opaProdNo", opaProdNo);
			return query.getResultList();
		} catch (NoResultException e) {
//			e.printStackTrace();
			return null;
		}
	}
	
}
