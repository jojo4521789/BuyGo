package web.front_end.member.pa.prod.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.pa.prod.entity.PaProd;

public interface ProdService extends CoreService {

	PaProd insert (PaProd prod);
	
	PaProd updata (PaProd prod);
	
	List<PaProd> findAll(Integer paprodno);

	boolean remove (Integer paprodno);
}
