package web.front_end.member.acc.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.front_end.member.acc.dao.MemberDao;
import web.front_end.member.acc.entity.Member;

public class MemberDaoImpl  implements MemberDao {

	@Override
	public int insert(Member entity) {
		getSession().persist(entity);
		return 1;
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
		final StringBuilder hql = new StringBuilder().append("UPDATE Member SET");
		int offset = 0;
		final String pw = entity.getMemberPw();
		if(pw != null && !pw.isEmpty()) {
			hql.append("pw = :memberPw,");
			offset = 1;
		}
		hql.append("MEMBER_NAME = :memberName,")
		   .append("MEMBER_ADD = :memberAdd,")
		   .append("MEMBER_PHONE = :memberPhone,")
		   .append("MEMBER_EMAIL = :memberEmail,")
		   .append("MEMBER_GENDER = :memberGender,")
		   .append("WHERE MEMBER_ACCT = :memberAcct");
		Query<?> query = getSession().createQuery(hql.toString());
		if(pw != null && !pw.isEmpty()) {
			query.setParameter("MEMBER_PW", pw);
		}
		
		return query.setParameter("memberName", entity.getMemberName())
					.setParameter("memberAdd", entity.getMemberAdd())
					.setParameter("memberPhone", entity.getMemberPhone())
					.setParameter("memberEmail", entity.getMemberEmail())
					.setParameter("memberGender", entity.getMemberGender())
					.executeUpdate();
	}

	@Override
	public Member selectById(Integer memberAcct) {
		return getSession().get(Member.class,memberAcct);
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
	
}
