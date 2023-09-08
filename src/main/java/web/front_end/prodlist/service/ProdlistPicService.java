package web.front_end.prodlist.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.prodlist.entity.PaProdPic;

public interface ProdlistPicService extends CoreService{
	
	List<PaProdPic> selectByPaProdId(Integer paProdNo);

}
