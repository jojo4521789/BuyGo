package web.back_end.opa.prod.service;

import java.util.List;

import core.service.CoreService;
import web.back_end.opa.prod.entity.Prod;

public interface ProdService extends CoreService{
	Prod add(Prod prod);
	
	Prod update(Prod prod);
	
	Prod prodSelectById(Integer opaProdNo);
	
	List<Prod> findAll();
	
	List<Prod> findPart(String input);
	
	boolean remove(Integer opaProdNo);
}
