package web.back_end.opa.prod.dao;

import core.dao.CoreDao;
import web.back_end.opa.prod.entity.Prcats;

public interface PrcatsDao extends CoreDao<Prcats, Integer>{
	Prcats SelectByOpaPrcatsName(String opaPrcatsName);
}
