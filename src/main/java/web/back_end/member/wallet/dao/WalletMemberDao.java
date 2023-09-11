package web.back_end.member.wallet.dao;


import static core.util.HibernateUtil.getSessionFactory;

import java.util.List;

import org.hibernate.Session;

import web.front_end.member.acc.entity.Member;

public interface WalletMemberDao{
	List<Member> select(Member member);
	int updateWalletAmountByMemberNoAndWalletAmount(Integer memberNo, Double memberWalletAmount);
	Member selectMemberByMemberNo(Integer memberNo);
	
	default Session getSession() {
		return getSessionFactory().getCurrentSession();
//		return getSessionFactory().openSession();
	}
}
