package web.front_end.prodlist.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.query.Query;

import web.front_end.prodlist.dao.PaProdPicDAO;
import web.front_end.member.pa.prodpic.entity.ProdPic;

public class PaProdPicDAOImpl implements PaProdPicDAO{
	
	//用paProdNo去select此paProdNo所有的圖片
	@Override
	public ProdPic selectById(Integer paProdNo) {
		return getSession().get(ProdPic.class, paProdNo);
	}
	
	@Override
	public List<ProdPic> SelectByPaProdNo(Integer paProdNo){
		try {
			Query<ProdPic> query = getSession()
					.createQuery("FROM ProdPic WHERE paProdNo =:paProdNo", ProdPic.class)
					.setParameter("paProdNo", paProdNo);
			return query.getResultList();
		} catch (NoResultException e) {
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int insert(ProdPic entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ProdPic entity) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<ProdPic> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
