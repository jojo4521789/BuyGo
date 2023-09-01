package web.front_end.member.pa.prodpic.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.pa.prodpic.entity.ProdPic;

public interface ProdPicService extends CoreService {
	
	ProdPic insert(ProdPic prodpic);
	
	ProdPic update(ProdPic prodpic);
	
	List<ProdPic> SelectByProdId(Integer paprodno);
	
	List<ProdPic> findAll();
	
	boolean remove(Integer paprodno);

}
