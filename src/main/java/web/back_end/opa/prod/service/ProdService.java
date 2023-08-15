package web.back_end.opa.prod.service;

import java.util.List;

import core.service.CoreService;
import web.back_end.opa.prod.entity.Prod;

public interface ProdService extends CoreService{
	Prod add(Prod prod);
	
	Prod update(Prod prod);
	
	List<Prod> findAll();
	
	boolean remove(Integer opaProdNo);
}
