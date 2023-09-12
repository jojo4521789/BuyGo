package web.front_end.member.gpa.order.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import web.front_end.member.gpa.order.dao.GpaProdRpDao;
import web.front_end.member.gpa.order.entity.GpaProdRp;
import web.front_end.seller.gpa.prod.entity.GpaProd;

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

	@Override
	public List<GpaProdRp> selectByProdNo() {
		Session session = getSession();
		String sqlQuery = "select a.GPA_PROD_NO,a.MEMBER_NO,a.GPA_CATS_NO,a.GPA_PROD_NAME,a.GPA_PROD_CONTENT from GPA_PROD a join GPA_PROD_RP b on a.GPA_PROD_NO = b.GPA_PROD_NO\r\n"
				+ "union select c.PA_PROD_NO,c.MEMBER_NO,c.PA_PROD_OBJ_NO,c.PA_PROD_NAME,c.PA_PROD_CONTENT from PA_PROD  c join GPA_PROD_RP b on c.PA_PROD_NO = b.PA_PROD_NO;";
		NativeQuery<GpaProdRp> prodRp = session.createNativeQuery(sqlQuery,GpaProdRp.class);
		
		return prodRp.getResultList();
	}

	

}
