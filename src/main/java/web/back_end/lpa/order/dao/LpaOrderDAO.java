package web.back_end.lpa.order.dao;

import core.dao.CoreDao;
import web.back_end.lpa.order.entity.Lpa_Prod;
import web.back_end.lpa.order.entity.Lpa_SO;

public interface LpaOrderDAO extends CoreDao<Lpa_SO, Integer> {
	
//	void deleteByProdNo(Integer lpaOrderNo);
//	int updateByProdNo(Lpa_SO lpa_SO);
//	Lpa_Prod findByPK(Integer lpaProdNo);
}
