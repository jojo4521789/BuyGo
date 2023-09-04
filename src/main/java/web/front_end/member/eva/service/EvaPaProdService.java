package web.front_end.member.eva.service;

import java.util.List;

import web.front_end.member.eva.entity.PaProd;

public interface EvaPaProdService {
	List<PaProd> loadByMemberNo(Integer memberNo);
}
