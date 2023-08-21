package web.front_end.member.lpa.cart.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import web.front_end.member.lpa.cart.dao.LpaProdDao;
import web.front_end.member.lpa.cart.entity.LpaProd;

public class LpaProdDaoImpl implements LpaProdDao {

	@Override
	public int insert(LpaProd lpaProd) {
		getSession().persist(lpaProd);
		return 1;
	}

	@Override
	public int deleteById(Integer lpaProdNo) {
		Session session = getSession();
		LpaProd lpaProd = session.get(LpaProd.class, lpaProdNo);
		session.remove(lpaProd);
		return 0;
	}

	@Override
	public int update(LpaProd lpaProd) {
		Session session = getSession();
		LpaProd oldLpaProd = session.get(LpaProd.class, lpaProd.getLpaProdNo());
		final String lpaProdName = lpaProd.getLpaProdName();
		if (lpaProdName != null) {
			oldLpaProd.setLpaProdName(lpaProdName);
		}
		final String lpaProdContent = lpaProd.getLpaProdContent();
		if (lpaProdContent != null) {
			oldLpaProd.setLpaProdContent(lpaProdContent);
		}
		final Integer lpaProdCatsNo = lpaProd.getLpaProdCatsNo();
		if (lpaProdCatsNo != null) {
			oldLpaProd.setLpaProdCatsNo(lpaProdCatsNo);
		}
		final Integer lpaProdStock = lpaProd.getLpaProdStock();
		if (lpaProdStock != null) {
			oldLpaProd.setLpaProdStock(lpaProdStock);
		}
		final Integer lpaProdSold = lpaProd.getLpaProdSold();
		if(lpaProdSold != null) {
			oldLpaProd.setLpaProdSold(lpaProdSold);
		}
		final Integer lpaProdPrice = lpaProd.getLpaProdPrice();
		if (lpaProdPrice != null) {
			oldLpaProd.setLpaProdPrice(lpaProdPrice);
			}
		final Timestamp lpaProdOffTime = lpaProd.getLpaProdOffTime();
		if(lpaProdOffTime != null) {
			oldLpaProd.setLpaProdOffTime(lpaProdOffTime);
		}
		final Integer lpaProdStatus = lpaProd.getLpaProdStatus();
		if(lpaProdStatus != null) {
			oldLpaProd.setLpaProdStatus(lpaProdStatus);
		}
		return 1;
	}

	@Override
	public LpaProd selectById(Integer lpaProdNo) {
		return getSession().get(LpaProd.class, lpaProdNo);
	}

	@Override
	public List<LpaProd> selectAll() {
		final String hql = "FROM LpaProd ORDER BY lpaProdNo";
		return getSession()
				.createQuery(hql, LpaProd.class)
				.getResultList();
	}

	@Override
	public LpaProd SelectByLpaProdName(String LpaProdName) {
		// TODO Auto-generated method stub
		return null;
	}

}
