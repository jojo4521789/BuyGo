package web.back_end.opa.prod.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.back_end.opa.prod.dao.PrcatsDao;
import web.back_end.opa.prod.entity.Prcats;

public class PrcatsDaoImpl implements PrcatsDao{

	@Override
	public int insert(Prcats prcats) {
		getSession().persist(prcats);
		return 1;
	}

	@Override
	public int deleteById(Integer opaPrcatsNo) {
		Session session = getSession();
		Prcats prcats = session.get(Prcats.class, opaPrcatsNo);
		session.remove(prcats);
		return 1;
	}

	@Override
	public int update(Prcats prcats) {
		Session session = getSession();
		Prcats oldPrcats = session.get(Prcats.class, prcats.getOpaPrcatsNo());
		final String opaPrcatsName = prcats.getOpaPrcatsName();
		if(opaPrcatsName != null) {
			oldPrcats.setOpaPrcatsName(opaPrcatsName);
		}
		return 1;
	}

	@Override
	public Prcats selectById(Integer opaPrcatsNo) {
		return getSession().get(Prcats.class, opaPrcatsNo);
	}

	@Override
	public List<Prcats> selectAll() {
		final String hql = "FROM Prcats ORDER BY opaPrcatsNo";
		return  getSession().createQuery(hql, Prcats.class).getResultList();
	}

	@Override
	public Prcats SelectByOpaPrcatsName(String opaPrcatsName) {
		try {
			Query<Prcats> query = getSession().createQuery("FROM Prcats WHERE opaPrcatsName = :opaPrcatsName", Prcats.class).setParameter("opaPrcatsName", opaPrcatsName);
			return query.getSingleResult();
		} catch (NoResultException e) {
//			e.printStackTrace();
			return null;
		}
	}
	
}
