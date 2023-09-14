package web.front_end.guest.paProd.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaBuilder.In;

import net.bytebuddy.asm.Advice.Return;
import web.front_end.guest.paProd.dao.PaProdDao;
import web.front_end.guest.prod.entity.OpaProducts;
import web.front_end.member.pa.prod.entity.PaProd;

public class PaProdDaoImpl implements PaProdDao{
	
	public Class<PaProd> getEntityClass() {
		return PaProd.class;
	}
	
	@Override
	public List<PaProd> selectAll() {
		return getSession().createQuery("FROM" + getEntityClass().getSimpleName(), getEntityClass()).getResultList();
	}

	public List<PaProd> selectAll(String[] categoryFilters, String minPrice, String maxPrice, String page, int productPerPage) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<PaProd> criteriaQuery = builder.createQuery(this.getEntityClass());
		Root<PaProd> root = criteriaQuery.from(this.getEntityClass());
		criteriaQuery.select(root);
        List<Predicate> predicates = new ArrayList<>();
        
        if(categoryFilters != null) {
        	In<Integer> inClause = builder.in(root.get("opaPrcats").<Integer>get("opaPrcatsNo"));
        }
	}
}
