package web.front_end.member.pa.order.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import core.util.HibernateUtil;
import web.front_end.member.pa.order.dao.PaSoDAO;
import web.front_end.member.pa.order.entity.PaSo;

public class PaSoDAOImpl implements PaSoDAO {
	private static final long serialVersionUID = 1L;

	public Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public int insert(PaSo paSo) {
		getSession().persist(paSo);
		return paSo.getPaSoNo(); // 回傳產生的識別值
	}

	public int deleteById(Integer id) {
		Session session = getSession();
		PaSo paSo = session.get(PaSo.class, id);
		session.remove(paSo);
		return 1;
	}

	public PaSo selectById(Integer id) {
		return getSession().get(PaSo.class, id);
	}

	public List<PaSo> selectAll() {
		// HQL寫法
		final String hql = "FROM PaSo ORDER BY paSoNo DESC";
		return getSession().createQuery(hql, PaSo.class).getResultList();
	}

	public List<PaSo> selectAllByStatus(Byte status) {
		// HQL寫法
		final String hql = "FROM PaSo WHERE paSoStatus = :status ORDER BY id DESC";
		return getSession()
				.createQuery(hql, PaSo.class)
				.setParameter("status", status)
				.getResultList();
	}

//	public List<PaSo> selectAllByMember(Integer memberNo) {
//		// HQL寫法
//		final String hql = "FROM PaSo WHERE memberNo = :buyerNo ORDER BY PaSoNo"; // 以買家角度查詢全部訂單
//		return getSession()
//				.createQuery(hql, PaSo.class)
//				.setParameter("buyerNo", memberNo)
//				.getResultList();
//	}

	public PaSo selectByOrderSeq(String paSoSeq) {
		final String hql = "FROM PaSo WHERE paSoSeq = :seq";
		return getSession()
				.createQuery(hql, PaSo.class)
				.setParameter("seq", paSoSeq)
				.getSingleResult();
	}

	public List<PaSo> selectByBuyerNo(Integer buyerNo, Byte status) {
		final String hql = "FROM PaSo WHERE memberNo = :buyerNo AND paSoStatus = :status";
		return getSession().createQuery(hql, PaSo.class).setParameter("buyerNo", buyerNo)
				.setParameter("status", status).getResultList();
	}

	@Override
	public int update(PaSo entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateSoSeq(Integer nextSoId, String soSeq) {
		// TODO Auto-generated method stub
		final String hql = "UPDATE PaSo SET paSoSeq = :seq WHERE id = :id";
		return getSession()
				.createQuery(hql)
				.setParameter("seq", soSeq)
				.setParameter("id", nextSoId)
				.executeUpdate();
	}

	public int updateSoStatus(Integer PaSoNo, Byte status) {
		final String hql = "UPDATE PaSo SET paSoStatus = :status WHERE id = :id";
		getSession().createQuery(hql)
		.setParameter("status", status)
		.setParameter("id", PaSoNo)
		.executeUpdate();
		return 1;
	}
	
	public int updateSoDeliverDetails(Integer PaSoNo, String msg) {
		final String hql = "UPDATE PaSo SET paDeliverTime = :currentTime, paDeliverMsg = :msg WHERE id = :id";
		Timestamp currentTime = new Timestamp(System.currentTimeMillis()); // 取得現在時間
		
		getSession().createQuery(hql)
		.setParameter("currentTime", currentTime)
		.setParameter("msg", msg)
		.setParameter("id", PaSoNo)
		.executeUpdate();
		return 1;
	}

}
