package web.front_end.member.pa.cart.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.pa.cart.entity.PaCart;
import web.front_end.member.pa.cart.entity.PaCartId;

public interface PaCartDAO extends CoreDao<PaCart, Integer>{

	int deleteById(PaCartId paCartId);
	
	PaCart selectById(PaCartId paCartId);
	
	List<PaCart> selectByMemberNo(Integer memberNo);
	
	List<PaCart> selectBySellerNo(Integer memberNo);

}
