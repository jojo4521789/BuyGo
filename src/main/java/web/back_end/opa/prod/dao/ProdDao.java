package web.back_end.opa.prod.dao;

import java.util.List;

import core.dao.CoreDao;
import web.back_end.opa.prod.entity.Prod;

public interface ProdDao extends CoreDao<Prod, Integer> {
	int updateProdStatus(Prod prod);

	List<Prod> selectByOpaProdName(String opaProdName);

	List<Prod> selectByOpaProdStatus(Integer opaProdStatus);

	List<Prod> selectProdWithLimit(Integer limit, Integer offset);

	List<Prod> selectByOpaProdNameWithLimit(String opaProdName, Integer limit, Integer offset);

	List<Prod> selectByOpaPrcatsNoWithLimit(List<Integer> opaPrcatsNoList, Integer limit, Integer offset);

	int getProdTotalQty();

	int getProdTotalQtySelectByOpaProdName(String opaProdName);

	int getProdTotalQtySelectByOpaPrcatsNo(List<Integer> opaPrcatsNoList);

	List<Prod> getRandomProdsByPrcatsWithLimit(Integer opaProdNo, Integer opaPrcatsNo, Integer limit);

	List<Prod> getRandomProdsWithLimit(Integer limit);
}
