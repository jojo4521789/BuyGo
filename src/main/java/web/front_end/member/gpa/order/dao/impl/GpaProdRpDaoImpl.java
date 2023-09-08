package web.front_end.member.gpa.order.dao.impl;

import java.util.List;

import org.hibernate.Session;

import web.front_end.member.gpa.order.dao.GpaProdRpDao;
import web.front_end.member.gpa.order.entity.GpaProdRp;

public class GpaProdRpDaoImpl implements GpaProdRpDao{

	@Override
	public int insert(GpaProdRp entity) {
		getSession().persist(entity);
		return 1;
	}

	@Override
	public int deleteById(Integer gpaProdRpNo) {
		Session session = getSession();
		GpaProdRp gpaProdRp = session.get(GpaProdRp.class, gpaProdRpNo);
		session.remove(gpaProdRp);
		return 1;
	}

	@Override
	public int update(GpaProdRp entity) {
		return 0;
	}

	@Override
	public GpaProdRp selectById(Integer gpaProdRpNo) {
		return getSession().get(GpaProdRp.class, gpaProdRpNo);
	}

	@Override
	public List<GpaProdRp> selectAll() {
		final String hql = "FROM Gpa_Prod_RP ORDER BY GPA_PROD_RP_NO";
		return getSession().createQuery(hql,GpaProdRp.class).getResultList();
	}

}
