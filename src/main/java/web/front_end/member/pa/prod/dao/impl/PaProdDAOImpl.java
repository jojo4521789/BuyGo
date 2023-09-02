package web.front_end.member.pa.prod.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.front_end.member.pa.prod.dao.PaProdDAO;
import web.front_end.member.pa.prod.entity.PaProd;

public class PaProdDAOImpl implements PaProdDAO {

	@Override
	public int insert(PaProd prod) {
		getSession().save(prod);
		return 1;
	}

	@Override
	public int deleteById(Integer paProdNo) {
		Session session = getSession();
		PaProd prod = session.get(PaProd.class, paProdNo);
		session.remove(prod);
		return 1;
	}

	@Override
	public int update(PaProd prod) {
		final StringBuilder hql = new StringBuilder().append("UPDATE Prod SET ");
		hql.append("paProdName= :paProdName, ")
				.append("paProdStockQty= :paProdStockQty, ")
				.append("paProdShipQty= :paProdShipQty, ")
				.append("paProdPrice= :paProdPrice, ")
				.append("paProdContent= :paProdContent, ")
				.append("paProdStatus= :paProdStatus, ")
				.append("paProdObjNo= :paProdObjNo ")
				.append("WHERE paProdNo= :paProdNo");
		Query<?> query = getSession().createQuery(hql.toString());
		return query.setParameter("paProdName", prod.getPaProdName())
				.setParameter("paProdStockQty", prod.getPaProdStockQty())
				.setParameter("paProdShipQty", prod.getPaProdShipQty())
				.setParameter("paProdPrice", prod.getPaProdPrice())
				.setParameter("paProdContent", prod.getPaProdContent())
				.setParameter("paProdStatus", prod.getPaProdStatus())
				.setParameter("paProdObjNo", prod.getPaProdObjNo())
				.setParameter("paProdNo", prod.getPaProdNo())
				.executeUpdate();
	}

	@Override
	public PaProd selectById(Integer paProdNo) {
		return getSession().get(PaProd.class, paProdNo);
	}

	@Override
	public List<PaProd> selectAll() {
		final String hql = "FROM Prod ORDER BY paProdNo";
		return getSession().createQuery(hql, PaProd.class).getResultList();
	}

	@Override
	public List<PaProd> selectByProdNo(String paProdNo) {
		try {
			Query<PaProd> query = getSession().createQuery("FROM Prod WHERE paProdNo = :paProdNo", PaProd.class)
					.setParameter("paProdNo", paProdNo);
			return query.getResultList();
		} catch (NoResultException e) {
//			e.printStackTrace();
			return null;
		}
	}

}
