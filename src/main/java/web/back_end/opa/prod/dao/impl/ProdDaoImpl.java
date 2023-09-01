package web.back_end.opa.prod.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.back_end.opa.prod.dao.ProdDao;
import web.back_end.opa.prod.entity.Prod;

public class ProdDaoImpl implements ProdDao{

	@Override
	public int insert(Prod prod) {
		getSession().persist(prod);
		return 1;
	}

	@Override
	public int deleteById(Integer opaProdNo) {
		Session session = getSession();
		Prod prod = session.get(Prod.class, opaProdNo);
		session.remove(prod);
		return 1;
	}

	@Override
	public int update(Prod prod) {
		final StringBuilder hql = new StringBuilder().append("UPDATE Prod SET ");
		hql.append("opaPrcatsNo= :opaPrcatsNo")
			.append("opaProdName= :opaProdName")
			.append("opaProdStockQty= :opaProdStockQty")
			.append("opaProdShipQty= :opaProdShipQty")
			.append("opaProdPrice= :opaProdPrice")
			.append("opaProdUrl= :opaProdUrl")
			.append("opaProdStatus= :opaProdStatus");
		Query<?> query = getSession().createQuery(hql.toString());
		return query.setParameter("opaPrcatsNo", prod.getOpaPrcatsNo())
				.setParameter("opaProdName", prod.getOpaProdName())
				.setParameter("opaProdStockQty", prod.getOpaProdStockQty())
				.setParameter("opaProdShipQty", prod.getOpaProdShipQty())
				.setParameter("opaProdPrice", prod.getOpaProdPrice())
				.setParameter("opaProdUrl", prod.getOpaProdUrl())
				.setParameter("opaProdStatus", prod.getOpaProdStatus())
				.executeUpdate();
	}

	@Override
	public Prod selectById(Integer opaProdNo) {
		return getSession().get(Prod.class, opaProdNo);
	}

	@Override
	public List<Prod> selectAll() {
		final String hql = "FROM Prod ORDER BY opaProdNo";
		return getSession().createQuery(hql, Prod.class).getResultList();
	}

	@Override
	public List<Prod> SelectByOpaProdName(String opaProdName) {
		try {
			Query<Prod> query = getSession().createQuery("FROM Prod WHERE opaProdName = :opaProdName", Prod.class).setParameter("opaProdName", opaProdName);
			return query.getResultList();
		} catch (NoResultException e) {
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateProdStatus(Prod prod) {
		Session session = getSession();
		Prod oldProd = session.get(Prod.class, prod.getOpaProdNo());
		final Integer opaProdStatus = prod.getOpaProdStatus();
		if(opaProdStatus != null) {
			oldProd.setOpaProdStatus(opaProdStatus);
		}
		return 1;
	}

	@Override
	public List<Prod> SelectByOpaProdStatus(Integer opaProdStatus) {
		try {
			Query<Prod> query = getSession().createQuery("FROM Prod WHERE opaProdStatus = :opaProdStatus", Prod.class)
					.setParameter("opaProdStatus", opaProdStatus);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
