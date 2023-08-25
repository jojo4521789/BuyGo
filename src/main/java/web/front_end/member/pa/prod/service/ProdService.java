package web.front_end.member.pa.prod.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.pa.prod.entity.Prod;

public interface ProdService extends CoreService {

	Prod insert (Prod prod);
	
	Prod updata (Prod prod);
	
	List<Prod> findAll(Integer paprodno);

	boolean remove (Integer paprodno);
}
