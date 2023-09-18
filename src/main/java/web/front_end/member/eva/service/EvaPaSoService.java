package web.front_end.member.eva.service;

import java.util.List;

//import web.front_end.member.eva.entity.PaSo;
import web.front_end.member.pa.order.entity.PaSo;

public interface EvaPaSoService {
	List<PaSo> loadBySellerMemberNo(Integer memberNo);
	List<PaSo> loadBySellerMemberNoAndPaSoStatus(Integer memberNo, Integer paSoStatus);
}
