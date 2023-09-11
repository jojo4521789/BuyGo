package web.front_end.member.eva.dao;

import static core.util.HibernateUtil.getSessionFactory;

import java.util.List;

import org.hibernate.Session;

import web.front_end.member.eva.entity.PaSo;
import web.front_end.seller.gpa.prod.entity.GpaProd;

public interface EvaPaSoDao{

	List<PaSo> selectAll();
	
	default Session getSession() {
		return getSessionFactory().getCurrentSession();
//		return getSessionFactory().openSession();
	}
}
