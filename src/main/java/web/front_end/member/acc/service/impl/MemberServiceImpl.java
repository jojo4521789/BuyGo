package web.front_end.member.acc.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import web.front_end.member.acc.dao.MemberDao;
import web.front_end.member.acc.dao.impl.MemberDaoImpl;
import web.front_end.member.acc.entity.Member;
import web.front_end.member.acc.service.MemberService;
@Service
@Transactional
public class MemberServiceImpl implements MemberService{
	private MemberDao dao;
	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
	}
	@Override
	public Member register(Member member) {
		if(member.getMemberAcct() == null) {
			member.setMessage("未輸入使用者帳號");
			member.setSuccessful(false);
			return member;

		}
		if(member.getMemberPw() == null) {
			member.setMessage("未輸入使用者密碼");
			member.setSuccessful(false);
			return member;

		}
		if(member.getMemberStatus() == null) {
			member.setMessage("未輸入申請身分");
			member.setSuccessful(false);
			return member;
		}
		if(member.getMemberName() == null) {
			member.setMessage("未輸入使用者名稱");
			member.setSuccessful(false);
			return member;
		}
		if(member.getMemberAdd() == null) {
			member.setMessage("未輸入使用者地址");
			member.setSuccessful(false);
			return member;
		}
		if(member.getMemberPhone() == null) {
			member.setMessage("未輸入使用者電話");
			member.setSuccessful(false);
			return member;
		}
		if(member.getMemberEmail() == null) {
			member.setMessage("未輸入使用者信箱");
			member.setSuccessful(false);
			return member;
		}
		if(member.getMemberGender() == null) {
			member.setMessage("未輸入使用者性別");
			member.setSuccessful(false);
			return member;
		}
		if(member.getMemberBirthday() == null) {
			member.setMessage("未輸入使用者生日");
			member.setSuccessful(false);
			return member;
		}
		if(member.getMemberId() == null) {
			member.setMessage("未輸入使用者身分證");
			member.setSuccessful(false);
			return member;
		}
			

		final int resultcount = dao.insert(member);
		if(resultcount != 1) {
			member.setMessage("新增錯誤");
			member.setSuccessful(false);
			return member;
		}
		member.setMessage("註冊成功");
		member.setSuccessful(true);
		return member;
	}

	@Override
	public Member edit(Member member) {
		
		final int resultCount = dao.update(member);
		member.setSuccessful(resultCount > 0);
		member.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		return member;
	}

	@Override
	public Member login(Member member) {
		return null;
	}

	@Override
	public List<Member> findall() {
		return dao.selectAll();
	}

	@Override
	public boolean remove(Integer id) {
		return dao.deleteById(id) > 0;
	}

	@Override
	public boolean save(Member member) {
		return dao.update(member) > 0;
	}
	@Override
	public Member forgetMember(String email) {
		return dao.selectByMemberEmail(email);
	}


}
