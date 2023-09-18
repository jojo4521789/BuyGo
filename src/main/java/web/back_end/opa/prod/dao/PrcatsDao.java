package web.back_end.opa.prod.dao;

import java.util.List;

import core.dao.CoreDao;
import web.back_end.opa.prod.entity.Prcats;

public interface PrcatsDao extends CoreDao<Prcats, Integer>{
	Prcats SelectByOpaPrcatsName(String opaPrcatsName);
	
	List<Prcats> SelectByOpaPrcatsNameList(String opaPrcatsName);
}
