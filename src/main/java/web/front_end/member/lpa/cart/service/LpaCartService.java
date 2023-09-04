package web.front_end.member.lpa.cart.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.lpa.cart.entity.LpaCart;

public interface LpaCartService extends CoreService {
	   LpaCart add(LpaCart lpaCart);
	   
	   LpaCart update(LpaCart lpaCart);
	   
	   List<LpaCart> finall();
	   
	   boolean remove(Integer lpaProdNo);
}