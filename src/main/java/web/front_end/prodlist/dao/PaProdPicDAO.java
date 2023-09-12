package web.front_end.prodlist.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.pa.prodpic.entity.ProdPic;

public interface PaProdPicDAO extends CoreDao<ProdPic, Integer>{
	
	List<ProdPic> SelectByPaProdNo(Integer paProdNo);
	

}
