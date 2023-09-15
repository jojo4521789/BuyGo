package web.front_end.member.pa.prodpic.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.pa.prodpic.entity.PaProdPic;

public interface ProdPicService extends CoreService {
	
	PaProdPic insert(PaProdPic prodpic);
	
	PaProdPic update(PaProdPic prodpic);
	
	List<PaProdPic> SelectByProdId(Integer paprodno);
	
	List<PaProdPic> findAll();
	
	boolean remove(Integer paprodno);

}
