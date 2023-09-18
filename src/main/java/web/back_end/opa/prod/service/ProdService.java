package web.back_end.opa.prod.service;

import java.util.List;

import core.service.CoreService;
import web.back_end.opa.prod.entity.Prod;

public interface ProdService extends CoreService{
	Prod add(Prod prod);
	
	Prod update(Prod prod);
	
	Prod updateProdStatus(Prod prod);
	
	Prod prodSelectById(Integer opaProdNo);
	
	List<Prod> findAll();
	
	List<Prod> findPart(String input);
	
	List<Prod> getOnOffShelfProds(Integer opaProdStatus);
	
	boolean remove(Integer opaProdNo);
	
	List<Prod> findAllProdWithLimit(Integer limit, Integer offset);
	
	List<Prod> findByOpaProdNameWithLimit(String opaProdName, Integer limit, Integer offset);
	
	List<Prod> findByOpaPrcatsNoWithLimit(List<Integer> opaPrcatsNoList, Integer limit, Integer offset);
	
	int getProdTotalQty();
	
	int getProdTotalQtySelectByOpaProdName(String opaProdName);
	
	int getProdTotalQtySelectByOpaPrcatsNo(List<Integer> opaPrcatsNoList);
	
	List<Prod> getRandomProdsByPrcatsWithLimit(Integer opaProdNo, Integer opaPrcatsNo, Integer limit);
	
	List<Prod> getRandomProdsWithLimit(Integer limit);
}
