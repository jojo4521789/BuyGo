package web.back_end.lpa.order.dao;

import java.util.List;

import core.dao.CoreDao;
import web.back_end.lpa.order.entity.Lpa_SO;
import web.back_end.lpa.product.entity.Lpa_Prod;

public interface LpaOrderDAO extends CoreDao<Lpa_SO, Integer> {
	
	Lpa_SO selectByOrderSeq(Lpa_SO lpa_SO);
	List<Lpa_SO> selectByOrderStatus(Byte status);
//	void deleteByProdNo(Integer lpaOrderNo);
//	int updateByProdNo(Lpa_SO lpa_SO);
//	Lpa_Prod findByPK(Integer lpaProdNo);
}
