package web.front_end.member.opa.order.dao.impl;
import java.io.Serializable;

import web.front_end.member.opa.order.dao.OpaOrderDao;
import web.front_end.member.opa.order.entity.*;
import java.util.List; 
import javax.persistence.criteria.*;

import org.hibernate.query.Query;

public class OpaOrderDaoImpl implements OpaOrderDao {

	public Class<OpaOrder> getEntityClass() {
		return OpaOrder.class;
	}

	@Override
	public List<OpaOrder> selectAll() {
		return getSession().createQuery("from " + getEntityClass().getSimpleName(), getEntityClass()).getResultList();
	}

	@Override
	public List<OpaOrder> selectAllByMember(int memberNo) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<OpaOrder> criteriaQuery = builder.createQuery(this.getEntityClass());
        Root<OpaOrder> root = criteriaQuery.from(this.getEntityClass());
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("memberNo"), memberNo));
        criteriaQuery.orderBy(builder.desc(root.get("opaSoNo")));
        
        Query<OpaOrder> query = getSession().createQuery(criteriaQuery);
        List<OpaOrder>results = query.getResultList();
        
        return results;
	}

	

    @Override
	public List<OpaOrder> selectAllByStatus(int memberNo, int status) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<OpaOrder> criteriaQuery = builder.createQuery(this.getEntityClass());
        Root<OpaOrder> root = criteriaQuery.from(this.getEntityClass());
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("memberNo"), memberNo), builder.equal(root.get("opaSoStatus"), status));
        criteriaQuery.orderBy(builder.desc(root.get("opaSoNo")));
        
        Query<OpaOrder> query = getSession().createQuery(criteriaQuery);
        List<OpaOrder>results = query.getResultList();
        
        return results;
    }

    public Serializable save(OpaOrder entity) {
		return getSession().save(entity);
	}

	public Serializable save(OpaOrderdetails entity) {
		return getSession().save(entity);
	}

    public void persist(OpaOrder entity) {
		getSession().persist(entity);
	}
    
    @Override
    public int insert(OpaOrder entity) {
		return (int)save(entity);
	}

    @Override
    public OpaOrder selectById(Integer id) {
		return getSession().get(getEntityClass(), id);
	}
    
    public void delete(OpaOrder entity) {
		getSession().delete(entity);
	}
   
    @Override
	public int deleteById(Integer id) {
		OpaOrder entity = selectById(id);
		delete(entity);
		return 1;
	}

    @Override
	public int update(OpaOrder entity) {
		getSession().update(entity);
		return 1;
	}

	public void saveOrUpdate(OpaOrder entity) {
		getSession().saveOrUpdate(entity);
	}
} 
