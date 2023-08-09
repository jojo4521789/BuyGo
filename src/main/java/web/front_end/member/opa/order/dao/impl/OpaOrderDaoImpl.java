package web.front_end.member.opa.order.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import web.front_end.member.opa.order.dao.OpaOrderDao;
import web.front_end.member.opa.order.entity.OpaOrder;
import web.front_end.member.opa.order.entity.OpaOrderDetails;

import javax.transaction.Transactional;
import java.util.List; 

@Repository
@Transactional
public class OpaOrderDaoImpl implements OpaOrderDao {

    private final SessionFactory sessionFactory;

    public OpaOrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public OpaOrder getOrderById(Integer orderId) {
        return getSession().get(OpaOrder.class, orderId);
    }

    @Override
    public List<OpaOrder> getAllOrders() {
        final String hql = "from OpaOrder";
        return executeQuery(hql, OpaOrder.class);
    }

    @Override
    public void saveOrder(OpaOrder order) {
        getSession().persist(order);
    }

    @Override
    public void updateOrder(OpaOrder order) {
        getSession().update(order);
    }

    @Override
    public void deleteOrder(OpaOrder order) {
        getSession().delete(order);
    }

    @Override
    public List<OpaOrderDetails> getOrderDetailsByOrderId(Integer orderId) {
        final String hql = "from OpaOrderDetails where opaOrder.soNo = :orderId";
        return executeQueryWithParam(hql, OpaOrderDetails.class, "orderId", orderId);
    }

    @Override
    public void saveOrderDetails(OpaOrderDetails orderDetails) {
        getSession().persist(orderDetails);
    }

    @Override
    public void updateOrderDetails(OpaOrderDetails orderDetails) {
        getSession().update(orderDetails);
    }

    @Override
    public void deleteOrderDetails(OpaOrderDetails orderDetails) {
        getSession().delete(orderDetails);
    }

    @Override
    public List<OpaOrder> selectAll() {
        final String hql = "from OpaOrder"; 
        return executeQuery(hql, OpaOrder.class);
    }

    @Override
    public int insert(OpaOrder order) {
        getSession().persist(order);
        return 1; // 或者返回其他適當的值，表示插入成功
    }

    @Override
    public int update(OpaOrder order) {
        getSession().update(order);
        return 1; // 或者返回其他適當的值，表示更新成功
    }

    @Override
    public OpaOrder selectById(Integer id) {
        return getOrderById(id);
    }

    @Override
    public int deleteById(Integer id) {
        OpaOrder order = getOrderById(id);
        if (order != null) {
            getSession().delete(order);
            return 1;
        }
        return 0;
    }

    private <T> List<T> executeQuery(String hql, Class<T> clazz) {
        return getSession().createQuery(hql, clazz).getResultList();
    }

    private <T> List<T> executeQueryWithParam(String hql, Class<T> clazz, String paramName, Object paramValue) {
        return getSession().createQuery(hql, clazz).setParameter(paramName, paramValue).getResultList();
    }
} 
