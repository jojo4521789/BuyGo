package web.back_end.lpa.order.dao;

import java.util.List;

import core.dao.CoreDao;
import web.back_end.lpa.order.entity.LpaSo;
import web.back_end.lpa.product.entity.LpaProd;

public interface LpaSoDAO extends CoreDao<LpaSo, Integer> {
	
	List<LpaSo> selectAll();
	List<LpaSo> selectByBuyerNo(Integer buyerNo, Byte status);
	List<LpaSo> selectAllByStatus(Byte status);
	int updateSoSeq(Integer nextSoId, String soSeq);
	LpaSo selectByOrderSeq(String lpaSoSeq);
	int updateSoStatus(Integer lpaSoNo, Byte status);
}
