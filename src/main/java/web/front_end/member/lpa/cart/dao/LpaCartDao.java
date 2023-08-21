package web.front_end.member.lpa.cart.dao;

import core.dao.CoreDao;
import web.front_end.member.lpa.cart.entity.LpaCart;

public interface LpaCartDao extends CoreDao<LpaCart, Integer>{
	LpaCart SelectByLpaCartName(String lpaCartName);
	
}
