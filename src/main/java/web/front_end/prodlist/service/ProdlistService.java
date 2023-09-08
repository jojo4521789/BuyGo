package web.front_end.prodlist.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.prodlist.entity.PaProdlist;

public interface ProdlistService extends CoreService {

	PaProdlist ProdInfoSelectByNo(Integer paProdNo);
	
	List<PaProdlist> RangetProdNo(Integer limit, Integer paProdObjNo);
	
}
