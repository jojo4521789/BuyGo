package web.front_end.prodlist.dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import web.front_end.prodlist.dao.ProdlistDao;
import web.front_end.prodlist.entity.PaProdlist;

public class ProdlistDAOImpl implements ProdlistDao{
	
	//用商品編號查詢該筆商品的所有資料
	public PaProdlist selectByPaProdNo(Integer paProdNo) {
		String hql = "FROM Prodlist WHERE paProdNo = :paProdNo";
		Query<PaProdlist> query = getSession().createQuery(hql, PaProdlist.class);
		query.setParameter("paProdNo", paProdNo);
	    return query.uniqueResult();
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
	public PaProdlist selectById(Integer id) {
		return null;
	}

	@Override
	public List<PaProdlist> selectAll() {
		return null;
	}


}
