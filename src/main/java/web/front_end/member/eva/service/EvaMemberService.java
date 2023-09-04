package web.front_end.member.eva.service;

import web.front_end.member.acc.entity.Member;

public interface EvaMemberService {
	Member loadMemberByMemberNo(Integer memberNo);
}
