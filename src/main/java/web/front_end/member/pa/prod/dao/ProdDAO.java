package web.front_end.member.pa.prod.dao;

import core.dao.CoreDao;
import web.front_end.member.pa.prod.entity.Prod;

public interface ProdDAO extends CoreDao<Prod, Integer> {
	Prod selectByProdNo(String prodno);
}
