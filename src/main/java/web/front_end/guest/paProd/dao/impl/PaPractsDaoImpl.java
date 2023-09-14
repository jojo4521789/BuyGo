package web.front_end.guest.paProd.dao.impl;

import java.io.Serializable;
import java.util.List;

import web.front_end.guest.paProd.dao.PaPractsDao;
import web.front_end.guest.paProd.entity.PaProdCats;

public class PaPractsDaoImpl implements PaPractsDao {
	public Class<PaProdCats> getEntityClass() {
		return PaProdCats.class;
	}

	@Override
	public List<PaProdCats> selectAll() {
		return getSession().createQuery("FROM " + getEntityClass().getSimpleName(), getEntityClass()).getResultList();
	}
	
	public Serializable save(PaProdCats entity) {
		return getSession().save(entity);
	}
	
	public void persist(PaProdCats entity) {
		getSession().persist(entity);
	}
	
	@Override
	public int insert(PaProdCats entity) {
		return (int)save(entity);
	}
	
	public PaProdCats selectById(Integer id) {
		return getSession().get(getEntityClass(), id);
	}
	
	public void delete(PaProdCats entity) {
		getSession().delete(entity);
	}
	
	@Override
	public int deleteById(Integer id) {
		PaProdCats entity = selectById(id);
		delete(entity);
		return 1;
	}
		
	@Override
	public int update(PaProdCats entity) {
		getSession().update(entity);
		return 1;
	}
	
	public void saveOrUpdate(PaProdCats entity) {
		getSession().saveOrUpdate(entity);
	}
}
