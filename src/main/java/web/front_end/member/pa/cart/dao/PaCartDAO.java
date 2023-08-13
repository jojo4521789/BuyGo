package web.front_end.member.pa.cart.dao;

import core.dao.CoreDao;
import web.front_end.member.pa.cart.entity.PaCart;

public interface PaCartDAO extends CoreDao<PaCart, Integer>{

	int deleteByPaProdNo(PaCart paProdNo);

}
