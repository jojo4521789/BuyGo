package web.front_end.member.acc.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.acc.entity.Member;

public interface MemberService extends CoreService {
	Member register(Member member);
	Member edit(Member member);
	Member login(Member member);
	List<Member> findall();
	boolean remove(Integer id);
	boolean save(Member member);
	Member forgetMember(String email);
	Member selectById(Integer id);
	Member checkMemberAcct(String memberAcct);
}
