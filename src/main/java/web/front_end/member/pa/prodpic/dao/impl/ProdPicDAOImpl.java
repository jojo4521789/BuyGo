package web.front_end.member.pa.prodpic.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.front_end.member.pa.prodpic.dao.ProdPicDAO;
import web.front_end.member.pa.prodpic.entity.ProdPic;

public class ProdPicDAOImpl implements ProdPicDAO{

	@Override
	public int insert(ProdPic prodPic) {
		getSession().save(prodPic);
		return 1;
	}

	@Override
	public int deleteById(Integer paProdNo) {
		Session session = getSession();
		ProdPic prodPic = session.get(ProdPic.class, paProdNo);
		session.remove(prodPic);
		return 1;
	}

	@Override
	public int update(ProdPic prodPic) {
		Session session = getSession();
		ProdPic oldProdPic = session.get(ProdPic.class, prodPic.getPaProdNo());
		final Integer paProdNo = prodPic.getPaProdNo();
		if(paProdNo != null) {
			oldProdPic.setPaProdNo(paProdNo);
		}
		final String paProdPicture = prodPic.getPaProdPic();
		if(paProdPicture != null) {
			oldProdPic.setPaProdPic(paProdPicture);
		}
		return 1;
	}

	@Override
	public ProdPic selectById(Integer paProdPicNo) {
		return getSession().get(ProdPic.class, paProdPicNo);
	}

	@Override
	public List<ProdPic> selectAll() {
		final String hql = "FROM ProdPic ORDER BY paProdPicNo";
		return getSession().createQuery(hql, ProdPic.class).getResultList();
	}


	@Override
	public List<ProdPic> selectByPicNo(Integer prodPicNo) {
		try {
			Query<ProdPic> query = getSession().createQuery("FROM Prpics WHERE paProdNo = :paProdNo", ProdPic.class).setParameter("paProdNo", prodPicNo);
			return query.getResultList();
		} catch (NoResultException e) {
//			e.printStackTrace();
			return null;
		}
	}

}
