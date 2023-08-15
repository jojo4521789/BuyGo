package web.back_end.opa.prod.service;

import java.util.List;

import core.service.CoreService;
import web.back_end.opa.prod.entity.Prcats;

public interface PrcatsService extends CoreService{
	Prcats add(Prcats prcats);
	
	Prcats update(Prcats prcats);
	
	List<Prcats> findAll();
	
	boolean remove(Integer opaPrcatsNo);
}
