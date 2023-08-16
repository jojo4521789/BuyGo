package web.back_end.opa.prod.dao;

import java.util.List;

import core.dao.CoreDao;
import web.back_end.opa.prod.entity.Prod;

public interface ProdDao extends CoreDao<Prod, Integer>{
	List<Prod> SelectByOpaProdName(String opaProdName);
}
