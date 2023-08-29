package web.front_end.prodlist.service;

import core.service.CoreService;
import web.front_end.prodlist.entity.PaProdlist;

public interface ProdlistService extends CoreService {

	PaProdlist ProdInfo(Integer paProdNo);
	
}
