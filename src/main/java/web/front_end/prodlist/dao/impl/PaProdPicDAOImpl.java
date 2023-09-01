package web.front_end.prodlist.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.query.Query;

import web.front_end.prodlist.dao.PaProdPicDAO;
import web.front_end.prodlist.entity.PaProdPic;

public class PaProdPicDAOImpl implements PaProdPicDAO{
	
	//用paProdNo去select此paProdNo所有的圖片
	@Override
	public PaProdPic selectById(Integer paProdNo) {
		return getSession().get(PaProdPic.class, paProdNo);
	}
	
	@Override
	public List<PaProdPic> SelectByPaProdNo(Integer paProdNo){
		try {
			Query<PaProdPic> query = getSession()
					.createQuery("FROM PaProdPic WHERE paProdNo =:paProdNo", PaProdPic.class)
					.setParameter("paProdNo", paProdNo);
			return query.getResultList();
		} catch (NoResultException e) {
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int insert(PaProdPic entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(PaProdPic entity) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<PaProdPic> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
