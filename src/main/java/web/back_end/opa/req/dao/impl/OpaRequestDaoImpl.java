package web.back_end.opa.req.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import web.back_end.opa.req.dao.OpaRequestDao;
import web.back_end.opa.req.entity.OpaRequest;

public class OpaRequestDaoImpl implements OpaRequestDao {

    private SessionFactory sessionFactory;

    public OpaRequestDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public OpaRequest getById(Integer id) {
        return getSession().get(OpaRequest.class, id);
    }

    @Override
    public List<OpaRequest> getAll() {
        return executeQuery("from OpaRequest", OpaRequest.class);
    }

    @Override
    public List<OpaRequest> getByStatus(Integer status) {
        return executeQuery("from OpaRequest where opaRequestStatus = :status", OpaRequest.class, "status", status);
    }

    @Override
    public void saveOpaRequest(OpaRequest opaRequest) {
        getSession().persist(opaRequest);
    }

    @Override
    public void updateOpaRequest(OpaRequest opaRequest) {
        getSession().update(opaRequest); // 直接使用 update 方法
    }

    @Override
    public void deleteOpaRequest(OpaRequest opaRequest) {
        getSession().delete(opaRequest);
    }

    @Override
    public int deleteById(Integer id) {
        OpaRequest opaRequest = getById(id);
        if (opaRequest != null) {
            getSession().delete(opaRequest);
            return 1;
        }
        return 0;
    }
    
    @Override
    public List<OpaRequest> selectAll() {
        return executeQuery("from OpaRequest", OpaRequest.class);
    }

    @Override
    public int update(OpaRequest opaRequest) {
        getSession().update(opaRequest); // 直接使用 update 方法
        return 1;
    }

    @Override
    public OpaRequest selectById(Integer id) {
        return getById(id);
    }
    
    @Override
    public int insert(OpaRequest opaRequest) {
        getSession().persist(opaRequest);
        return 1;
    }
    
    private <T> List<T> executeQuery(String hql, Class<T> clazz) {
        return getSession().createQuery(hql, clazz).getResultList();
    }

    private <T> List<T> executeQuery(String hql, Class<T> clazz, String paramName, Object paramValue) {
        return getSession().createQuery(hql, clazz)
            .setParameter(paramName, paramValue)
            .getResultList();
    }
}