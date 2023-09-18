package web.front_end.member.eva.service;

import java.util.List;

import web.front_end.seller.gpa.prod.entity.GpaProd;

public interface EvaGpaProdService {
	List<GpaProd> loadByMemberNo(Integer memberNo);
}
