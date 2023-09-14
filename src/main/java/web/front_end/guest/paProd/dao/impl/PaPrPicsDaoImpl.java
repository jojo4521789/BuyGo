package web.front_end.guest.paProd.dao.impl;

import java.io.Serializable;
import java.util.List;

import web.front_end.guest.paProd.dao.PaPrPicsDao;
import web.front_end.member.pa.prodpic.entity.ProdPic;

public class PaPrPicsDaoImpl implements PaPrPicsDao {
	
	public Class<ProdPic> getEntityClass() {
		return ProdPic.class;
	}

	@Override
	public List<ProdPic> selectAll() {
		return getSession().createQuery("FROM" + getEntityClass().getSimpleName(), getEntityClass()).getResultList();
	}
	
	public Serializable save(ProdPic entity) {
		return getSession().save(entity);
	}
	
	public void persist(ProdPic entity) {
		getSession().persist(entity);
	}
	
	public int insert(ProdPic entity) {
		return (int)save(entity);
	}
	
	@Override
	public ProdPic selectById(Integer id) {
		return getSession().get(getEntityClass(), id);
	}
	
	public void delete(ProdPic entity) {
		getSession().delete(entity);
	}
	
	@Override
	public int deleteById(Integer id) {
		ProdPic entity = selectById(id);
		delete(entity);
		return 1;
	}
	
	@Override
	public int update(ProdPic entity) {
		getSession().update(entity);
		return 1;
	}
	
	public void saveOrUpdate(ProdPic entity) {
		getSession().saveOrUpdate(entity);
	}


}
