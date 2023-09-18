package web.front_end.member.login.dao;

import static core.util.HibernateUtil.getSessionFactory;

import org.hibernate.Session;

import web.front_end.member.acc.entity.Member;

public interface LoginMemberDao{
	Member selectByMemberAcctAndMemberPw(String memberAcct, String memberPw);
	Member selectById(Integer memberNo);
	Member selectByMemberAcct(String memberAcct);
	Member selectByMemberEmail(String memberEmail);
	Member selectByMemberAcctAndMemberEmail(String memberAcct, String memberEmail);
	int updateMemberPwByMemberAcct(String memberAcct, String memberPw);
	
	default Session getSession() {
		return getSessionFactory().getCurrentSession();
//		return getSessionFactory().openSession();
	}
}
