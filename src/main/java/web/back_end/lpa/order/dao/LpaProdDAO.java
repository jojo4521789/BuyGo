package web.back_end.lpa.order.dao;

import java.util.List;

import web.back_end.lpa.order.entity.Lpa_Prod;

public interface LpaProdDAO {
	void add(Lpa_Prod lpaProd);
	void deleteByProdNo(Integer lpaProdNo);
	int updateByProdNo(Lpa_Prod lpaProd);
//	Lpa_Prod findByPK(Integer lpaProdNo);
//	List<Lpa_Prod> getAll();
}
