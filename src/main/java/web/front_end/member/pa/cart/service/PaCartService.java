package web.front_end.member.pa.cart.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.pa.cart.entity.PaCart;
import web.front_end.member.pa.cart.entity.PaCartId;

public interface PaCartService extends CoreService{
	
	PaCart add(PaCart paCart);

	PaCart update(PaCart paCart);

	List<PaCart> findAll();

	boolean remove(PaCartId paCartId);

	PaCart selectById(PaCartId paCartId);

	List<PaCart> selectByMemberNo(Integer memberNo);
	
}
