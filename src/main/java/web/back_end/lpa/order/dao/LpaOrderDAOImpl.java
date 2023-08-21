package web.back_end.lpa.order.dao;

import java.util.List;

import org.hibernate.Session;

import core.util.HibernateUtil;
import web.back_end.lpa.order.entity.Lpa_SO;

public class LpaOrderDAOImpl implements LpaOrderDAO {
	private static final long serialVersionUID = 1L;
	public Session getSession () {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public int insert(Lpa_SO lpa_SO) {
		getSession().persist(lpa_SO);
		return 1;
	}

	public int deleteById(Integer orderNo) {
		Session session = getSession();
		Lpa_SO lpa_SO = session.get(Lpa_SO.class, orderNo);
		session.remove(lpa_SO);
		return 1;
	}
	
	public Lpa_SO selectById(Integer orderNo) {
		return getSession().get(Lpa_SO.class, orderNo);
	}
	
	public List<Lpa_SO> selectAll() {
		// HQL寫法
		final String hql = "FROM Lpa_SO ORDER BY lpaSoNo";
		return getSession()
				.createQuery(hql, Lpa_SO.class)
				.getResultList();
	}
	
	public Lpa_SO selectByOrderSeq(Lpa_SO lpa_SO) {
		final String hql = "FROM Lpa_SO WHERE lpaSoSeq = :seq";
		return getSession()
				.createQuery(hql, Lpa_SO.class)
				.setParameter("seq", lpa_SO.getLpaSoSeq())
				.getSingleResult();
	}
	
	public List<Lpa_SO> selectByOrderStatus(Byte status) {
		final String hql = "FROM Lpa_SO WHERE lpaSoStatus = :status";
		return getSession()
				.createQuery(hql, Lpa_SO.class)
				.setParameter("status", status)
				.getResultList();
	}

	@Override
	public int update(Lpa_SO entity) {
		// TODO Auto-generated method stub
		return 0;
	}
	
//	public int update(Lpa_SO lpa_SO) {
//		final StringBuilder hql = new StringBuilder()
//				.append("UPDATE lpa_so SET ");
//			final String password = member.getPassword();
//			if (password != null && !password.isEmpty()) {
//				hql.append("password = :password,");
//			}
//			hql.append("nickname = :nickname,")
//				.append("pass = :pass,")
//				.append("roleId = :roleId,")
//				.append("updater = :updater,")
//				.append("lastUpdatedDate = NOW() ")
//				.append("WHERE username = :username");
//			
//			Query<?> query = getSession().createQuery(hql.toString());
//			if(password != null && !password.isEmpty()) {
//				query.setParameter("password", password);
//			}
//			
//			return query
//				.setParameter("nickname", member.getNickname())
//				.setParameter("pass", member.getPass())
//				.setParameter("roleId", member.getRoleId())
//				.setParameter("updater", member.getUpdater())
//				.setParameter("username", member.getUsername())
//				.executeUpdate();
//	}
}
