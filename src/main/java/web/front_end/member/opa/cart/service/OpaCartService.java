package web.front_end.member.opa.cart.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.opa.cart.entity.OpaCart;
import web.front_end.member.opa.cart.entity.OpaCartId;

public interface OpaCartService extends CoreService{
	OpaCart add(OpaCart opaCart);
	
	OpaCart update(OpaCart opaCart);
	
	List<OpaCart> findAll();
	
	boolean remove(OpaCartId opaCartId);
	
	OpaCart selectById(OpaCartId opaCartId);
	
	List<OpaCart> selectByMemberNo(Integer memberNo);
}
