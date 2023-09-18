package web.back_end.opa.coupon.dao;

import static core.util.HibernateUtil.getSessionFactory;

import java.util.List;

import org.hibernate.Session;

import web.back_end.opa.coupon.entity.CpOwner;
import web.back_end.opa.coupon.entity.CpOwnerId;

public interface CpOwnerDao{
	int insert(CpOwner cpOwner);

	int deleteById(CpOwnerId cpOwnerId);

	int update(CpOwner cpOwner);

	CpOwner selectById(CpOwnerId cpOwnerId);
	
	List<CpOwner> selectByMember(Integer memberNo);

	List<CpOwner> selectAll();

	default Session getSession() {
		return getSessionFactory().getCurrentSession();
//		return getSessionFactory().openSession();
	}
}
