package web.front_end.member.pa.cart;

import core.dao.CoreDao;

public interface PaCartDAO extends CoreDao<PaCart, Integer>{

	int deleteByPaProdNo(PaCart paProdNo);

}
