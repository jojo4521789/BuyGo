package web.front_end.member.pa.prod.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.front_end.member.pa.prod.dao.ProdDAO;
import web.front_end.member.pa.prod.entity.Prod;

public class ProdDAOImpl implements ProdDAO {

	@Override
	public int insert(Prod prod) {
		getSession().persist(prod);
		return 1;
	}

	@Override
	public int deleteById(Integer paProdNo) {
		Session session = getSession();
		Prod prod = session.get(Prod.class, paProdNo);
		session.remove(prod);
		return 1;
	}

	@Override
	public int update(Prod prod) {
		final StringBuilder hql = new StringBuilder().append("UPDATE Prod SET ");
		hql.append("paPrcatsNo= :paPrcatsNo")
			.append("paProdName= :paProdName")
			.append("paProdStockQty= :paProdStockQty")
			.append("paProdShipQty= :paProdShipQty")
			.append("paProdPrice= :paProdPrice")
			.append("paProdUrl= :paProdUrl")
			.append("paProdStatus= :paProdStatus");
		Query<?> query = getSession().createQuery(hql.toString());
		return query.setParameter("paProdNo", prod.getPaProdNo())
				.setParameter("paProdName", prod.getPaProdName())
				.setParameter("paProdStockQty", prod.getPaProdStockQty())
				.setParameter("paProdShipQty", prod.getPaProdShipQty())
				.setParameter("paProdPrice", prod.getPaProdPrice())
				.setParameter("paProdContent", prod.getPaProdContent())
				.setParameter("paProdStatus", prod.getPaProdStatus())
				.executeUpdate();
	}

	@Override
	public Prod selectById(Integer paProdNo) {
		return getSession().get(Prod.class, paProdNo);
	}

	@Override
	public List<Prod> selectAll() {
		final String hql = "FROM Prod ORDER BY paProdNo";
		return getSession().createQuery(hql, Prod.class).getResultList();
	}

	@Override
	public List<Prod> selectByProdNo(String paProdname) {
		try {
			Query<Prod> query = getSession().createQuery("FROM Prod WHERE paProdName = :paProdName", Prod.class).setParameter("paProdName", paProdname);
			return query.getResultList();
		} catch (NoResultException e) {
//			e.printStackTrace();
			return null;
		}
	}

}
