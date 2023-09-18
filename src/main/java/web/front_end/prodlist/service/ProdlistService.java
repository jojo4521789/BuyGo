package web.front_end.prodlist.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.pa.prod.entity.PaProd;

public interface ProdlistService extends CoreService {

	PaProd ProdInfoSelectByNo(Integer paProdNo);
	
	List<PaProd> RangetProdNo(Integer limit, Integer paProdObjNo);
	
}
