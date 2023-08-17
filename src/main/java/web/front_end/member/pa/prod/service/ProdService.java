package web.front_end.member.pa.prod.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.pa.prod.entity.Prod;

public interface ProdService extends CoreService {

	Prod add (Prod prod);
	
	Prod updata (Prod prod);
	
	List<Prod> findAll();

	boolean remove (Integer prodno);
}
