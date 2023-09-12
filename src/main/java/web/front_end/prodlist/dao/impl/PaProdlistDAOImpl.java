package web.front_end.prodlist.dao.impl;

import java.util.List;

import web.front_end.prodlist.dao.PaProdlistDAO;
import web.front_end.member.pa.prod.entity.PaProd;

public class PaProdlistDAOImpl implements PaProdlistDAO {

	// 用商品編號查詢該筆商品的所有資料
	@Override
	public PaProd selectById(Integer paProdNo) {
		return getSession().get(PaProd.class, paProdNo);
	}
	
	//用商品類別編號隨機拿取商品資訊
	@Override
	public List<PaProd> SelectByPaProdNo(Integer paProdObjNo) {
		final String hql = "FROM PaProd WHERE paProdObjNo = :paProdObjNo ORDER BY RAND()";
		return getSession()
				.createQuery(hql, PaProd.class)
				.setParameter("paProdObjNo", paProdObjNo)
				.getResultList();
	}



	@Override
	public int insert(PaProd pojo) {
		return 0;
	}

	@Override
	public int deleteById(Integer id) {
		return 0;
	}

	@Override
	public int update(PaProd pojo) {
		return 0;
	}

	@Override
	public List<PaProd> selectAll() {
		return null;
	}

}
