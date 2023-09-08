package web.front_end.prodlist.service;

import core.service.CoreService;
import web.front_end.member.acc.entity.Member;

public interface ProdlistSellerService extends CoreService{
	
	Member getSellerName(Integer memberNo);

}
