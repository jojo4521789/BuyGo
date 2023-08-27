package web.front_end.member.login.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import web.front_end.member.acc.entity.Member;
import web.front_end.member.login.dao.LoginMemberDao;

public class LoginMemberDaoImpl implements LoginMemberDao{

	public static void main(String[] args) {
		LoginMemberDaoImpl loginDaoImpl = new LoginMemberDaoImpl();
		
		// 測試
		Session session = loginDaoImpl.getSession();

		String memberAcct = "Sam123";
		String memberPw = "123456";
		
		Transaction transaction = session.beginTransaction(); // 開始交易
		System.out.println(loginDaoImpl.updateMemberPwByMemberAcct(memberAcct, memberPw)); // 沒改動到反饋0，有改動到反饋1
		transaction.commit(); // 送交，同時會結束交易

	}
	@Override
	public Member selectByMemberAcctAndMemberPw(String memberAcct, String memberPw) {
		final String hql = "FROM Member WHERE memberAcct = :memberAcct AND memberPw = :memberPw ORDER BY memberNo";
		return getSession()
				.createQuery(hql, Member.class)
				.setParameter("memberAcct", memberAcct)
				.setParameter("memberPw", memberPw)
				.uniqueResult();
	}

	@Override
	public Member selectById(Integer memberNo) {
		return getSession().get(Member.class, memberNo);
	}

	@Override
	public Member selectByMemberAcct(String memberAcct) {
		final String hql = "FROM Member WHERE memberAcct = :memberAcct ORDER BY memberNo";
		return getSession()
				.createQuery(hql, Member.class)
				.setParameter("memberAcct", memberAcct)
				.uniqueResult();
	}

	@Override
	public Member selectByMemberEmail(String memberEmail) {
		final String hql = "FROM Member WHERE memberEmail = :memberEmail ORDER BY memberNo";
		return getSession()
				.createQuery(hql, Member.class)
				.setParameter("memberEmail", memberEmail)
				.uniqueResult();
	}

	@Override
	public Member selectByMemberAcctAndMemberEmail(String memberAcct, String memberEmail) {
		final String hql = "FROM Member WHERE memberAcct = : memberAcct AND memberEmail = :memberEmail ORDER BY memberNo";
		return getSession()
				.createQuery(hql, Member.class)
				.setParameter("memberAcct", memberAcct)
				.setParameter("memberEmail", memberEmail)
				.uniqueResult();
	}
	
	@Override
	public int updateMemberPwByMemberAcct(String memberAcct, String memberPw) {
		final String hql = "UPDATE Member SET memberPw = :memberPw WHERE memberAcct = :memberAcct";
		Query<?> query = getSession().createQuery(hql);
		return query
				.setParameter("memberPw", memberPw)
				.setParameter("memberAcct", memberAcct)
				.executeUpdate();
	}
	
}
