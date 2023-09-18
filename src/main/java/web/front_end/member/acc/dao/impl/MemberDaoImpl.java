package web.front_end.member.acc.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import web.front_end.member.acc.dao.MemberDao;
import web.front_end.member.acc.entity.Member;

//@Repository
public class MemberDaoImpl  implements MemberDao {

	@Override
	public int insert(Member entity) {
		try {
			getSession().persist(entity);
			return 1;
			
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int deleteById(Integer memberAcct) {
		Session session = getSession();
		Member member = session.get(Member.class,memberAcct);
		session.remove(member);
		return 1;
	}

	@Override
	public int update(Member entity) {
		Session session = getSession();
		Member oldMember = session.get(Member.class,entity.getMemberNo());
		final String memberAcct = entity.getMemberAcct();
		if(memberAcct != null) {
			oldMember.setMemberAcct(memberAcct);
		}
		final String memberPw = entity.getMemberPw();
		if(memberPw != null) {
			oldMember.setMemberPw(memberPw);
		}
		final Integer memberStatus = entity.getMemberStatus();
		if(memberStatus != null) {
			oldMember.setMemberStatus(memberStatus);
		}
		final String memberName = entity.getMemberName();
		if(memberName != null) {
			oldMember.setMemberName(memberName);
		}
		final String memberAdd = entity.getMemberAdd();
		if(memberAdd != null) {
			oldMember.setMemberAdd(memberAdd);
		}
		final String memberPhone = entity.getMemberPhone();
		if(memberPhone != null) {
			oldMember.setMemberPhone(memberPhone);
		}
		final String memberEmail = entity.getMemberEmail();
		if(memberEmail != null) {
			oldMember.setMemberEmail(memberEmail);
		}
		final Integer memberGender = entity.getMemberGender();
		if(memberGender != null) {
			oldMember.setMemberGender(memberGender);
		}
		final String memberBirthday = entity.getMemberBirthday();
		if(memberBirthday != null) {
			oldMember.setMemberBirthday(memberBirthday);
		}
		final String memberId = entity.getMemberId();
		if(memberId != null) {
			oldMember.setMemberId(memberId);
		}
		return 1;
	}

	@Override
	public Member selectById(Integer memberNo) {
		return getSession().get(Member.class,memberNo);
	}

	@Override
	public List<Member> selectAll() {
		final String hql = "FROM Member ORDER BY MEMBER_NO";
		return getSession().createQuery(hql,Member.class).getResultList();
	}

	@Override
	public Member selectBymemberPhone(String memberPhone) {
		return getSession().get(Member.class, memberPhone);
	}

	@Override
	public Member selectByMemberAcct(String memberAcct) {
		
		return getSession().get(Member.class,memberAcct);
	}

	@Override
	public Member selectByMemberEmail(String memberEmail) {

		return getSession().get(Member.class,memberEmail);
	}

}
