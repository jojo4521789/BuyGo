package web.front_end.member.login.service;

import web.front_end.member.acc.entity.Member;
import web.front_end.member.login.dto.LoginCheckDTO;

public interface LoginService {
	boolean CheckMemberAcctAndPassword(String memberAcct, String memberPw);
	Member LoadMemberAcctByMemberNo(Integer memberNo);
	Member LoadMemberBymemberAcctAndmemberPw(String memberAcct, String memberPw);
	Member LoadMemberByMemberAcct(String memberAcct);
	Member LoadMemberByMemberEmail(String memberEmail);
	Member LoadMemberByMemberAcctAndMemberEmail(String memberAcct, String memberEmail);
	boolean ResetMemberPwByMemberAcct(String memberAcct, String memberPw);
}
