package web.front_end.member.login.service.impl;

import web.front_end.member.acc.entity.Member;
import web.front_end.member.login.dao.LoginMemberDao;
import web.front_end.member.login.dao.impl.LoginMemberDaoImpl;
import web.front_end.member.login.dto.LoginCheckDTO;
import web.front_end.member.login.service.LoginService;
import static web.front_end.member.util.SHA256EncoderUtil.SHA256Encode;

public class LoginServiceImpl implements LoginService {
	private LoginMemberDao dao;

	public LoginServiceImpl() {
		dao = new LoginMemberDaoImpl();
	}

	@Override
	public boolean CheckMemberAcctAndPassword(String memberAcct, String memberPw) {
		if (dao.selectByMemberAcctAndMemberPw(memberAcct, memberPw) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Member LoadMemberBymemberAcctAndmemberPw(String memberAcct, String memberPw) {
		return dao.selectByMemberAcctAndMemberPw(memberAcct, memberPw);
	}

	@Override
	public Member LoadMemberAcctByMemberNo(Integer memberNo) {
		return dao.selectById(memberNo);
	}

	@Override
	public Member LoadMemberByMemberAcct(String memberAcct) {
		return dao.selectByMemberAcct(memberAcct);
	}

	@Override
	public Member LoadMemberByMemberEmail(String memberEmail) {
		return dao.selectByMemberEmail(memberEmail);
	}

	@Override
	public Member LoadMemberByMemberAcctAndMemberEmail(String memberAcct, String memberEmail) {
		return dao.selectByMemberAcctAndMemberEmail(memberAcct, memberEmail);
	}

	@Override
	public boolean ResetMemberPwByMemberAcct(String memberAcct, String memberPw) {
		String sha256MemberPw = SHA256Encode(memberPw);
		int resetStatus = dao.updateMemberPwByMemberAcct(memberAcct, sha256MemberPw);
		if(resetStatus == 1) { // 如果修改成功
			return true;
		} 
		else { // 如果修改失敗
			return false;
		}
	}
}
