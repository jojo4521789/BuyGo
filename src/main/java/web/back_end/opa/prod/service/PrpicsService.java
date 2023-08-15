package web.back_end.opa.prod.service;

import java.util.List;

import core.service.CoreService;
import web.back_end.opa.prod.entity.Prpics;

public interface PrpicsService extends CoreService{
	Prpics add(Prpics prpics);
	
	Prpics update(Prpics prpics);
	
	List<Prpics> SelectByProdId(Integer opaProdNo);
	
	List<Prpics> findAll();
	
	boolean remove(Integer opaPrpicsNo);
}
