package web.front_end.member.pa.order.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.pa.order.entity.PaSo;

public interface PaSoDAO extends CoreDao<PaSo, Integer> {
	
	List<PaSo> selectAll();
	List<PaSo> selectByBuyerNo(Integer buyerNo, Byte status);
	List<PaSo> selectAllByStatus(Byte status);
	int updateSoSeq(Integer nextSoId, String soSeq);
	PaSo selectByOrderSeq(String paSoSeq);
	int updateSoStatus(Integer paSoNo, Byte status);
	int updateSoDeliverDetails(Integer PaSoNo, String msg);
}
