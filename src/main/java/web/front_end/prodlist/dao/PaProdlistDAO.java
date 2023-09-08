package web.front_end.prodlist.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.prodlist.entity.PaProdlist;

public interface PaProdlistDAO extends CoreDao<PaProdlist, Integer>{
	
	List<PaProdlist> SelectByPaProdNo(Integer paProdObjNo);
	
}
