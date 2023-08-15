package web.front_end.prodlist.dao;

import core.dao.CoreDao;
import web.front_end.prodlist.entity.Prodlist;

public interface ProdlistDAO extends CoreDao<Prodlist, Integer> {
	Prodlist selectByProdNo(String prodno);
}
