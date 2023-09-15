package web.front_end.prodlist.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.pa.prod.entity.PaProd;

public interface PaProdlistDAO extends CoreDao<PaProd, Integer>{
	
	List<PaProd> SelectByPaProdNo(Integer paProdObjNo);
	
}
