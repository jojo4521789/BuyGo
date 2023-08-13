package web.front_end.member.opa.cart.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import web.front_end.member.opa.cart.dao.OpaCartDao;
import web.front_end.member.opa.cart.entity.OpaCart;
import web.front_end.member.opa.cart.entity.OpaCartPrimaryKey;

public class OpaCartDaoImpl implements OpaCartDao {

    private SessionFactory sessionFactory;

    public OpaCartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() { 
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public OpaCart getById(OpaCartPrimaryKey id) {
        return getSession().find(OpaCart.class, id);
    }
    
    @Override
    public int deleteById(OpaCartPrimaryKey id) {
        OpaCart opaCart = getById(id);
        if (opaCart != null) {
            getSession().delete(opaCart);
            return 1;
        }
        return 0;
    }

    @Override
    public List<OpaCart> getAll() {
        final String hql = "from OpaCart";
        return executeQuery(hql, OpaCart.class);
    }

    @Override
    public List<OpaCart> getByMember(Integer memberNo) {
        final String hql = "from OpaCart where memberNo = :memberNo";
        return executeQuery(hql, OpaCart.class, "memberNo", memberNo);
    }

    @Override
    public void saveOpaCart(OpaCart opaCart) {
        getSession().persist(opaCart);
    }

    @Override
    public void updateOpaCart(OpaCart opaCart) {
        getSession().update(opaCart);
    }

    @Override
    public void deleteOpaCart(OpaCart opaCart) {
        getSession().delete(opaCart);
    }

    @Override
    public int insert(OpaCart opaCart) {
        getSession().persist(opaCart);
        return 1;
    }

    @Override
    public int update(OpaCart opaCart) {
        getSession().update(opaCart);
        return 1;
    }

    @Override
    public List<OpaCart> selectAll() {
        final String hql = "from OpaCart";
        return executeQuery(hql, OpaCart.class);
    }

    @Override
    public OpaCart selectById(OpaCartPrimaryKey id) {
        return getSession().find(OpaCart.class, id);
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