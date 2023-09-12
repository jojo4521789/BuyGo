package web.front_end.guest.prod.dao.impl;

import java.util.*;
import java.io.Serializable;

import javax.persistence.criteria.*;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.query.Query;

import web.front_end.guest.prod.dao.ProdDao;
import web.front_end.guest.prod.entity.OpaProducts;

public class ProdDaoImpl implements ProdDao {

	public Class<OpaProducts> getEntityClass() {
		return OpaProducts.class;
	}
    
    @Override
	public List<OpaProducts> selectAll() {
		return getSession().createQuery("from " + getEntityClass().getSimpleName(), getEntityClass()).getResultList();
	}

    public List<OpaProducts> selectAll(String[] categoryFilters, String minPrice, String maxPrice, String page, int productPerPage) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<OpaProducts> criteriaQuery = builder.createQuery(this.getEntityClass());
        Root<OpaProducts> root = criteriaQuery.from(this.getEntityClass());
        criteriaQuery.select(root);
        List<Predicate> predicates = new ArrayList<>();
        
        if(categoryFilters != null) {
        	In<Integer> inClause = builder.in(root.get("opaPrcats").<Integer>get("opaPrcatsNo"));
            for(int i = 0; i < categoryFilters.length; i++)
            	inClause.value(Integer.parseInt(categoryFilters[i]));
            predicates.add(inClause);
        }
        if(minPrice != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.<Double>get("opaProdPrice"), Double.parseDouble(minPrice)));
        }
        if(maxPrice != null) {
            predicates.add(builder.lessThanOrEqualTo(root.<Double>get("opaProdPrice"), Double.parseDouble(maxPrice)));
        }
        Predicate combinedCondition = builder.and(predicates.toArray(new Predicate[0]));
        criteriaQuery.where(combinedCondition);
        Query<OpaProducts> query = getSession().createQuery(criteriaQuery);
        if(page != null) {
            query.setFirstResult((Integer.parseInt(page) - 1) * productPerPage);
            query.setMaxResults(productPerPage);
        }
        List<OpaProducts> opaProducts = query.getResultList();
        return opaProducts;
    }

    public Long countProducts(String[] categoryFilters, String minPrice, String maxPrice) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<OpaProducts> root = criteriaQuery.from(this.getEntityClass());
        criteriaQuery.select(builder.count(root));
        if(categoryFilters != null) {
        	In<Integer> inClause = builder.in(root.get("opaPrcats").<Integer>get("opaPrcatsNo"));
            for(int i = 0; i < categoryFilters.length; i++)
            	inClause.value(Integer.parseInt(categoryFilters[i]));
        }
        if(minPrice != null) {
            criteriaQuery.where(builder.greaterThanOrEqualTo(root.<Double>get("opaProdPrice"), Double.parseDouble(minPrice)));
        }
        if(maxPrice != null) {
            criteriaQuery.where(builder.lessThanOrEqualTo(root.<Double>get("opaProdPrice"), Double.parseDouble(maxPrice)));
        }
        Query<Long> query = getSession().createQuery(criteriaQuery);
        Long count = query.getSingleResult();
        return count;
    }

    public Serializable save(OpaProducts entity) {
		return getSession().save(entity);
	}

    public void persist(OpaProducts entity) {
		getSession().persist(entity);
	}
    
    @Override
    public int insert(OpaProducts entity) {
		return (int)save(entity);
	}

    @Override
    public OpaProducts selectById(Integer id) {
		return getSession().get(getEntityClass(), id);
	}
    
    public void delete(OpaProducts entity) {
		getSession().delete(entity);
	}
   
    @Override
	public int deleteById(Integer id) {
		OpaProducts entity = selectById(id);
		delete(entity);
		return 1;
	}

    @Override
	public int update(OpaProducts entity) {
		getSession().update(entity);
		return 1;
	}

	public void saveOrUpdate(OpaProducts entity) {
		getSession().saveOrUpdate(entity);
	}
}