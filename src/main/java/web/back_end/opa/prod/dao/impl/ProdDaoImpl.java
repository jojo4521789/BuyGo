package web.back_end.opa.prod.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.back_end.opa.prod.dao.ProdDao;
import web.back_end.opa.prod.entity.Prod;

public class ProdDaoImpl implements ProdDao {

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
		hql.append("opaPrcatsNo= :opaPrcatsNo, ")
			.append("opaProdName= :opaProdName, ")
			.append("opaProdStockQty= :opaProdStockQty, ")
			.append("opaProdShipQty= :opaProdShipQty, ")
			.append("opaProdPrice= :opaProdPrice, ")
			.append("opaProdContent= :opaProdContent, ")
			.append("opaProdUrl= :opaProdUrl, ")
			.append("opaProdStatus= :opaProdStatus ")
			.append("WHERE opaProdNo= :opaProdNo");
		Query<?> query = getSession().createQuery(hql.toString());
		return query.setParameter("opaPrcatsNo", prod.getOpaPrcatsNo())
				.setParameter("opaProdName", prod.getOpaProdName())
				.setParameter("opaProdStockQty", prod.getOpaProdStockQty())
				.setParameter("opaProdShipQty", prod.getOpaProdShipQty())
				.setParameter("opaProdPrice", prod.getOpaProdPrice())
				.setParameter("opaProdContent", prod.getOpaProdContent())
				.setParameter("opaProdUrl", prod.getOpaProdUrl())
				.setParameter("opaProdStatus", prod.getOpaProdStatus())
				.setParameter("opaProdNo", prod.getOpaProdNo())
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
			Query<Prod> query = getSession().createQuery("FROM Prod WHERE opaProdName = :opaProdName", Prod.class)
					.setParameter("opaProdName", opaProdName);
			return query.getResultList();
		} catch (NoResultException e) {
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Prod> SelectByOpaProdNameList(String opaProdName) {
		try {
			Query<Prod> query = getSession().createQuery("FROM Prod WHERE opaProdName LIKE :opaProdName", Prod.class)
					.setParameter("opaProdName", "%" + opaProdName + "%");
			return query.getResultList();
		} catch (NoResultException e) {
//			e.printStackTrace();
			return null;
		}
	}

}
