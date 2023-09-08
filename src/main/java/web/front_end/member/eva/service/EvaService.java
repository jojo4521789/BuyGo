package web.front_end.member.eva.service;

import java.util.List;

import web.front_end.member.eva.entity.PaSo;

public interface EvaService {
	List<PaSo> loadPaSoBySellerMemberNoAndPaSoStatus(Integer memberNo, Integer paSoStatus);
}
