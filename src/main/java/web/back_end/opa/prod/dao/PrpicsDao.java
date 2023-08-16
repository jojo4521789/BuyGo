package web.back_end.opa.prod.dao;

import java.util.List;

import core.dao.CoreDao;
import web.back_end.opa.prod.entity.Prpics;

public interface PrpicsDao extends CoreDao<Prpics, Integer>{
	List<Prpics> SelectByOpaProdNo(Integer opaProdNo);
}
