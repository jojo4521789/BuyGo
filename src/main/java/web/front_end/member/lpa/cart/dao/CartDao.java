package web.front_end.member.lpa.cart.dao;

import core.dao.CoreDao;
import web.front_end.member.lpa.cart.entity.Cart;

public interface CartDao extends CoreDao<Cart, Integer>{
	Cart  SelectByLpaCartName(String lpaCartName);
}
