package web.front_end.guest.prod.dao.impl;
import java.io.Serializable;
import java.util.List;

import web.front_end.guest.prod.dao.OpaPrcatsDao;
import web.front_end.guest.prod.entity.OpaPrcats;

public class OpaPrcatsDaoImpl implements OpaPrcatsDao {
	public Class<OpaPrcats> getEntityClass() {
		return OpaPrcats.class;
	}

	@Override
	public List<OpaPrcats> selectAll() {
		return getSession().createQuery("from " + getEntityClass().getSimpleName(), getEntityClass()).getResultList();
	}

	public Serializable save(OpaPrcats entity) {
		return getSession().save(entity);
	}

    public void persist(OpaPrcats entity) {
		getSession().persist(entity);
	}
    
    @Override
    public int insert(OpaPrcats entity) {
		return (int)save(entity);
	}

    @Override
    public OpaPrcats selectById(Integer id) {
		return getSession().get(getEntityClass(), id);
	}
    
    public void delete(OpaPrcats entity) {
		getSession().delete(entity);
	}
   
    @Override
	public int deleteById(Integer id) {
		OpaPrcats entity = selectById(id);
		delete(entity);
		return 1;
	}

    @Override
	public int update(OpaPrcats entity) {
		getSession().update(entity);
		return 1;
	}

	public void saveOrUpdate(OpaPrcats entity) {
		getSession().saveOrUpdate(entity);
	}
}