package web.front_end.prodlist.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.prodlist.entity.PaProdPic;

public interface PaProdPicDAO extends CoreDao<PaProdPic, Integer>{
	
	List<PaProdPic> SelectByPaProdNo(Integer paProdNo);
	

}
