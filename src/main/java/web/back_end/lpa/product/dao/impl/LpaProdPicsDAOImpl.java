package web.back_end.lpa.product.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.back_end.lpa.product.dao.LpaProdPicsDAO;
import web.back_end.lpa.product.entity.LpaProdPic;
import web.back_end.opa.prod.entity.Prpics;

public class LpaProdPicsDAOImpl implements LpaProdPicsDAO{

	public Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	@Override
	public int insert(LpaProdPic entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(LpaProdPic entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LpaProdPic selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LpaProdPic> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 依照商品號碼查詢第一張圖片
	public byte[] selectProdFirstPic(Integer lpaProdNo) {
		final String hql = "SELECT lpaProdPic from LpaProdPic where lpaProdNo = :prodNo ORDER BY id";
		
			byte[] pic = getSession().createQuery(hql, byte[].class)
			.setParameter("prodNo", lpaProdNo)
			.setMaxResults(1)
			.getSingleResult();
			
			return pic;
	}
}
