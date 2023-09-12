package web.front_end.member.pa.prod.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.pa.prod.entity.PaProd;

public interface PaProdDAO extends CoreDao<PaProd, Integer> {
	List<PaProd> selectByProdNo(String prodno);
}
