package web.front_end.prodlist.dao;

import core.dao.CoreDao;
import web.front_end.prodlist.entity.PaProdlist;

public interface ProdlistDao extends CoreDao<PaProdlist, Integer>{
	
	PaProdlist selectByPaProdNo(Integer paProdNo);
	
}
