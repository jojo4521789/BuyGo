package web.back_end.opa.req.dao.impl;

import java.util.List;
import java.io.Serializable;

import web.back_end.opa.req.dao.OpaRequestDao;
import web.back_end.opa.req.entity.OpaRequest;

public class OpaRequestDaoImpl implements OpaRequestDao {
	@SuppressWarnings("unchecked")
	@Override
    public List<OpaRequest> selectAll() {
        List<OpaRequest> list = getSession().createQuery("from " + this.getEntityClass().getSimpleName() + " order by opaRequestStatus, opaRequestNo desc").list();
        return list;
    }
    
    public Class<OpaRequest> getEntityClass() {
		return OpaRequest.class;
	}

    public Serializable save(OpaRequest entity) {
		return getSession().save(entity);
	}

    public void persist(OpaRequest entity) {
		getSession().persist(entity);
	}
    
    @Override
    public int insert(OpaRequest entity) {
		return (int)save(entity);
	}

    @Override
    public OpaRequest selectById(Integer id) {
		return getSession().get(getEntityClass(), id);
	}

	@Override
    public List<OpaRequest> selectByMember(Integer memberNo) {
		return getSession().createQuery("from " + this.getEntityClass().getSimpleName() + " where MEMBER_NO = :memberNo").setParameter("memberNo", memberNo).list();
	}
    
    public void delete(OpaRequest entity) {
		getSession().delete(entity);
	}
   
    @Override
	public int deleteById(Integer id) {
		OpaRequest entity = selectById(id);
		delete(entity);
		return 1;
	}

    @Override
	public int update(OpaRequest entity) {
		getSession().update(entity);
		return 1;
	}

	public void saveOrUpdate(OpaRequest entity) {
		getSession().saveOrUpdate(entity);
	}

}