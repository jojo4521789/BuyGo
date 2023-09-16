package web.front_end.guest.prod.dao.impl;
import java.io.Serializable;
import java.util.List;

import web.front_end.guest.prod.dao.OpaPrpicsDao;
import web.front_end.guest.prod.entity.OpaPrpics;

public class OpaPrpicsDaoImpl implements OpaPrpicsDao {
	
	public Class<OpaPrpics> getEntityClass() {
		return OpaPrpics.class;
	}

	@Override
	public List<OpaPrpics> selectAll() {
		return getSession().createQuery("from " + getEntityClass().getSimpleName(), getEntityClass()).getResultList();
	}

	public Serializable save(OpaPrpics entity) {
		return getSession().save(entity);
	}

    public void persist(OpaPrpics entity) {
		getSession().persist(entity);
	}
    
    @Override
    public int insert(OpaPrpics entity) {
		return (int)save(entity);
	}

    @Override
    public OpaPrpics selectById(Integer id) {
		return getSession().get(getEntityClass(), id);
	}
    
    public void delete(OpaPrpics entity) {
		getSession().delete(entity);
	}
   
    @Override
	public int deleteById(Integer id) {
		OpaPrpics entity = selectById(id);
		delete(entity);
		return 1;
	}

    @Override
	public int update(OpaPrpics entity) {
		getSession().update(entity);
		return 1;
	}

	public void saveOrUpdate(OpaPrpics entity) {
		getSession().saveOrUpdate(entity);
	}
}