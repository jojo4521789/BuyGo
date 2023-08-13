package web.back_end.opa.order.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import web.back_end.opa.order.dao.OpaOrderBackDao;
import web.back_end.opa.order.entity.OpaOrderBack;
import web.back_end.opa.order.entity.OpaOrderDetails;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OpaOrderBackDaoImpl implements OpaOrderBackDao {

    private final SessionFactory sessionFactory;

    public OpaOrderBackDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public OpaOrderBack getById(Integer orderId) {
        return getSession().get(OpaOrderBack.class, orderId);
    }
        

    @Override
    public List<OpaOrderBack> getAllOrderBacks() {
        final String hql = "from OpaOrderBack"; // 從 OpaOrderBack 表格中查詢所有訂單回覆資訊
        return executeQuery(hql, OpaOrderBack.class);
    }

    @Override
    public void saveOrderBack(OpaOrderBack orderBack) {
        getSession().persist(orderBack);
    }

    @Override
    public void updateOrderBack(OpaOrderBack orderBack) {
        getSession().update(orderBack);
    }

    @Override
    public void deleteOrderBack(OpaOrderBack orderBack) {
        getSession().delete(orderBack);
    }

    @Override
    public List<OpaOrderDetails> getOrderDetailsByOrderBackId(Integer orderBackId) {
        // 根據訂單回覆編號取得訂單詳細資訊列表
        final String hql = "from OpaOrderDetails where opaSoNo = :orderBackId";
        return executeQueryWithParam(hql, OpaOrderDetails.class, "orderBackId", orderBackId);
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
    public int insert(OpaOrderBack orderBack) {
        getSession().persist(orderBack);
        return 1;
    }
    
    @Override
    public int update(OpaOrderBack orderBack) {
        getSession().update(orderBack);
        return 1; // 或者返回其他適當的值，表示更新成功
    }
    
    @Override
    public OpaOrderBack selectById(Integer id) {
        return getById(id);
    }
    
    @Override
    public int deleteById(Integer id) {
        OpaOrderBack orderBack = getById(id);
        if (orderBack != null) {
            getSession().delete(orderBack);
            return 1;
        }
        return 0; 
    }
    
    @Override
    public List<OpaOrderBack> selectAll() {
        final String hql = "from OpaOrderBack"; // 從 OpaOrderBack 表格中查詢所有訂單回覆資訊
        return executeQuery(hql, OpaOrderBack.class);
    }

    private <T> List<T> executeQuery(String hql, Class<T> clazz) {
        return getSession().createQuery(hql, clazz).getResultList();
    }

    private <T> List<T> executeQueryWithParam(String hql, Class<T> clazz, String paramName, Object paramValue) {
        return getSession().createQuery(hql, clazz).setParameter(paramName, paramValue).getResultList();
    }
}