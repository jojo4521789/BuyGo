package web.back_end.lpa.order.dao.impl;

import java.util.List;

import org.hibernate.Session;

import core.util.HibernateUtil;
import web.back_end.lpa.order.dao.LpaSoDAO;
import web.back_end.lpa.order.entity.LpaSo;

public class LpaSoDAOImpl implements LpaSoDAO {
	private static final long serialVersionUID = 1L;
	public Session getSession () {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public int insert(LpaSo lpaSo) {
		getSession().persist(lpaSo); 
		return lpaSo.getLpaSoNo(); // 回傳產生的識別值
	}

	public int deleteById(Integer id) {
		Session session = getSession();
		LpaSo lpaSo = session.get(LpaSo.class, id);
		session.remove(lpaSo);
		return 1;
	}
	
	public LpaSo selectById(Integer id) {
		return getSession().get(LpaSo.class, id);
	}
	
	public List<LpaSo> selectAll() {
		// HQL寫法
		final String hql = "FROM LpaSo ORDER BY lpaSoNo";
		return getSession()
				.createQuery(hql, LpaSo.class)
				.getResultList();
	}
	
	public List<LpaSo> selectAllByMember(Integer memberNo) {
		// HQL寫法
		final String hql = "FROM LpaSo WHERE memberNo = :buyerNo ORDER BY lpaSoNo"; // 以買家角度查詢全部訂單
		return getSession()
				.createQuery(hql, LpaSo.class)
				.setParameter("buyerNo", memberNo)
				.getResultList();
	}
	
	public LpaSo selectByOrderSeq(String lpaSoSeq) {
		final String hql = "FROM LpaSo WHERE lpaSoSeq = :seq";
		return getSession()
				.createQuery(hql, LpaSo.class)
				.setParameter("seq", lpaSoSeq)
				.getSingleResult();
	}
	
	public List<LpaSo> selectByOrderStatus(Integer memberNo, Byte status) {
		final String hql = "FROM LpaSo WHERE memberNo = :buyerNo AND lpaSoStatus = :status";
		return getSession()
				.createQuery(hql, LpaSo.class)
				.setParameter("buyerNo", memberNo)
				.setParameter("status", status)
				.getResultList();
	}

	@Override
	public int update(LpaSo entity) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int updateSoSeq(Integer nextSoId, String soSeq) {
		// TODO Auto-generated method stub
		final String hql = "UPDATE LpaSo SET lpaSoSeq = :seq WHERE id = :id";
		return getSession()
				.createQuery(hql)
				.setParameter("seq", soSeq)
				.setParameter("id", nextSoId)
				.executeUpdate();
	}
	
	
	
//	public int updateSoSeq(Lpa_SO lpa_SO) {
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
