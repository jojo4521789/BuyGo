package web.front_end.prodlist.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.pa.prodpic.entity.ProdPic;

public interface ProdlistPicService extends CoreService{
	
	List<ProdPic> selectByPaProdId(Integer paProdNo);

}
