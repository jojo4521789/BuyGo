package web.front_end.prodlist.dao.impl;

import java.util.List;

import web.front_end.prodlist.dao.PaProdlistDAO;
import web.front_end.prodlist.entity.PaProdlist;

public class PaProdlistDAOImpl implements PaProdlistDAO {

	// 用商品編號查詢該筆商品的所有資料
	@Override
	public PaProdlist selectById(Integer paProdNo) {
		return getSession().get(PaProdlist.class, paProdNo);
	}
	
	//用商品編號隨機拿取商品資訊
	@Override
	public List<PaProdlist> SelectByPaProdNo(Integer paProdNo) {
		final String hql = "FROM PaProdlist WHERE paProdNo = :paProdNo ORDER BY RAND()";
		return getSession()
				.createQuery(hql, PaProdlist.class)
				.setParameter("paProdNo", paProdNo)
				.getResultList();
	}



	@Override
	public int insert(PaProdlist pojo) {
		return 0;
	}

	@Override
	public int deleteById(Integer id) {
		return 0;
	}

	@Override
	public int update(PaProdlist pojo) {
		return 0;
	}

	@Override
	public List<PaProdlist> selectAll() {
		return null;
	}

}
