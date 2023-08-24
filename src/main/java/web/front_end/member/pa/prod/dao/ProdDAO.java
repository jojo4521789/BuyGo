package web.front_end.member.pa.prod.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.pa.prod.entity.Prod;

public interface ProdDAO extends CoreDao<Prod, Integer> {
	List<Prod> selectByProdNo(String prodno);
}
