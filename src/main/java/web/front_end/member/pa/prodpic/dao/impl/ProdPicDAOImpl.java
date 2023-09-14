package web.front_end.member.pa.prodpic.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.front_end.member.pa.prodpic.dao.ProdPicDAO;
import web.front_end.member.pa.prodpic.entity.PaProdPic;

public class ProdPicDAOImpl implements ProdPicDAO{

	@Override
	public int insert(PaProdPic prodPic) {
		getSession().save(prodPic);
		return 1;
	}

	@Override
	public int deleteById(Integer paProdNo) {
		Session session = getSession();
		PaProdPic prodPic = session.get(PaProdPic.class, paProdNo);
		session.remove(prodPic);
		return 1;
	}

	@Override
	public int update(PaProdPic prodPic) {
		Session session = getSession();
		PaProdPic oldProdPic = session.get(PaProdPic.class, prodPic.getPaProdNo());
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
	public PaProdPic selectById(Integer paProdPicNo) {
		return getSession().get(PaProdPic.class, paProdPicNo);
	}

	@Override
	public List<PaProdPic> selectAll() {
		final String hql = "FROM PaProdPic ORDER BY paProdPicNo";
		return getSession().createQuery(hql, PaProdPic.class).getResultList();
	}


	@Override
	public List<PaProdPic> selectByPicNo(Integer prodPicNo) {
		try {
			Query<PaProdPic> query = getSession().createQuery("FROM Prpics WHERE paProdNo = :paProdNo", PaProdPic.class).setParameter("paProdNo", prodPicNo);
			return query.getResultList();
		} catch (NoResultException e) {
//			e.printStackTrace();
			return null;
		}
	}
	
	// 從資料庫查詢圖片
			public String selectProdFirstPic(Integer paProdNo) {
				final String hql = "SELECT paProdPic from PaProdPic where paProdNo = :prodNo ORDER BY id";

				String pic = getSession()
						.createQuery(hql, String.class)
						.setParameter("prodNo", paProdNo)
						.setMaxResults(1)
						.getSingleResult();

				return pic;
			}

}
