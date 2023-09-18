package web.front_end.member.eva.dao;

import static core.util.HibernateUtil.getSessionFactory;

import java.util.List;

import org.hibernate.Session;

//import web.front_end.member.eva.entity.PaProd;
import web.front_end.member.pa.prod.entity.PaProd;
import web.front_end.seller.gpa.prod.entity.GpaProd;

public interface EvaPaProdDao{

	List<PaProd> selectByMemberNo(Integer memberNo);
	
	default Session getSession() {
		return getSessionFactory().getCurrentSession();
//		return getSessionFactory().openSession();
	}
}
