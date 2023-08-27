package web.back_end.lpa.order.dao;

import java.util.List;

import core.dao.CoreDao;
import web.back_end.lpa.order.entity.LpaSo;
import web.back_end.lpa.product.entity.LpaProd;

public interface LpaSoDAO extends CoreDao<LpaSo, Integer> {
	
	List<LpaSo> selectByOrderStatus(Integer memberNo, Byte status);
	List<LpaSo> selectAllByMember(Integer memberNo);
	int updateSoSeq(Integer nextSoId, String soSeq);
	LpaSo selectByOrderSeq(String lpaSoSeq);
//	void deleteByProdNo(Integer lpaOrderNo);
//	int updateByProdNo(Lpa_SO lpa_SO);
//	Lpa_Prod findByPK(Integer lpaProdNo);
}
