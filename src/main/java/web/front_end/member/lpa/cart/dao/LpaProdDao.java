package web.front_end.member.lpa.cart.dao;

import core.dao.CoreDao;
import web.front_end.member.lpa.cart.entity.LpaProd;

public interface LpaProdDao extends CoreDao<LpaProd, Integer> {
	LpaProd SelectByLpaProdName(String LpaProdName);

}
