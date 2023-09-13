package web.front_end.member.pa.order.dao.impl;

import java.util.List;

import web.front_end.member.pa.order.dao.PaReturnDAO;
import web.front_end.member.pa.order.entity.PaReturn;
import web.front_end.member.pa.order.entity.PaSo;

public class PaReturnDAOImpl implements PaReturnDAO{

	@Override
	public int insert(PaReturn paReturn) {
		getSession().persist(paReturn);
		return paReturn.getPaRtnNo(); // 回傳產生的識別值
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(PaReturn entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PaReturn selectById(Integer paRtnNo) {
		return getSession().get(PaReturn.class, paRtnNo);
	}

	@Override
	public List<PaReturn> selectAll() {
		final String hql = "FROM PaReturn ORDER BY paRtnNo DESC";
		return getSession().createQuery(hql, PaReturn.class).getResultList();
	}
	
	public List<PaReturn> selectByPaSoNo(Integer paSoNo) {
		final String hql = "FROM PaReturn WHERE paSoNo = :paSoNo ORDER BY paRtnNo DESC";
		return getSession()
				.createQuery(hql, PaReturn.class)
				.setParameter("paSoNo", paSoNo)
				.getResultList();
	}
	
	public int updateRtnSeq(Integer nextSoId, String rtnSeq) {
		final String hql = "UPDATE PaReturn SET paRtnSeq = :seq WHERE id = :id";
		return getSession()
				.createQuery(hql)
				.setParameter("seq", rtnSeq)
				.setParameter("id", nextSoId)
				.executeUpdate();
	}
	
	public int updateReturnStatus(Integer paRtnNo, Integer newStatus) {
		final String hql = "UPDATE PaReturn SET paRtnStat = :newStatus WHERE id = :id";
		return getSession()
				.createQuery(hql)
				.setParameter("newStatus", newStatus)
				.setParameter("id", paRtnNo)
				.executeUpdate();
	}

}
