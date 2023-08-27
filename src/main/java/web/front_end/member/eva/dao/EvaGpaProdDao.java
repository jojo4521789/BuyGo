package web.front_end.member.eva.dao;

import static core.util.HibernateUtil.getSessionFactory;

import java.util.List;

import org.hibernate.Session;

import web.front_end.seller.gpa.prod.entity.GpaProd;

public interface EvaGpaProdDao{

	List<GpaProd> selectByMemberNo(Integer memberNo);
	
	default Session getSession() {
		return getSessionFactory().getCurrentSession();
//		return getSessionFactory().openSession();
	}
}
