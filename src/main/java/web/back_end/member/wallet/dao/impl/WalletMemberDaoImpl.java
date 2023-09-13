package web.back_end.member.wallet.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import web.back_end.member.wallet.dao.WalletMemberDao;
import web.front_end.member.acc.entity.Member;

public class WalletMemberDaoImpl implements WalletMemberDao {
	public static void main(String[] args) {
		WalletMemberDaoImpl walletMemberDaoImpl = new WalletMemberDaoImpl();
		// 測試
		// 查詢select
//		Session session = walletMemberDaoImpl.getSession();
//
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		Member member = new Member();
//
//		member.setMemberNo(1);
//		member.setMemberAcct("david");
//		member.setMemberPhone("091");
//		member.setMemberEmail("david");
//		member.setMemberId("A");
//
//		List<Member> memberList = walletMemberDaoImpl.select(member);
//		for (Member m : memberList) {
//			System.out.println("----------------");
//			System.out.println(m.getMemberNo());
//			System.out.println(m.getMemberAcct());
//			System.out.println(m.getMemberId());
//			System.out.println(m.getMemberAdd());
//			System.out.println(m.getMemberWalletAmount());
//			System.out.println("----------------");
//		}
//		transaction.commit(); // 送交，同時會結束交易
		
		// 修改updateWalletAmountByMemberNoAndWalletAmount
//		Session session = walletMemberDaoImpl.getSession();
//
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		
//		System.out.println(walletMemberDaoImpl.updateWalletAmountByMemberNoAndWalletAmount(2, (double)2000));
//		
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢
		Session session = walletMemberDaoImpl.getSession();

		Transaction transaction = session.beginTransaction(); // 開始交易
		
		Member member = walletMemberDaoImpl.selectMemberByMemberNo(2);
		System.out.println("member.getMemberNo():" + member.getMemberNo());
		System.out.println("member.getMemberAcct():" + member.getMemberAcct());
		System.out.println("member.getMemberWalletAmount():" + member.getMemberWalletAmount());
		
		transaction.commit(); // 送交，同時會結束交易
	}

	@Override
	public List<Member> select(Member member) {
		// NativeSQL
		// 前端輸入框 會員編號, 帳號, 電話, 電子信箱, 身份證字號
		// 若輸入框有輸入值，則加入SQL的模糊查詢，若無輸入值則不加入SQL查詢
		StringBuilder nativeSql = new StringBuilder();
		nativeSql.append("SELECT * FROM MEMBER WHERE ");

		if (member.getMemberNo() != null) {
			nativeSql.append("MEMBER_NO LIKE :memberNo AND ");
		}

		if (member.getMemberAcct() != null && !member.getMemberAcct().isEmpty()) {
			nativeSql.append("MEMBER_ACCT LIKE :memberAcct AND ");
		}

		if (member.getMemberPhone() != null && !member.getMemberPhone().isEmpty()) {
			nativeSql.append("MEMBER_PHONE LIKE :memberPhone AND ");
		}

		if (member.getMemberEmail() != null && !member.getMemberEmail().isEmpty()) {
			nativeSql.append("MEMBER_EMAIL LIKE :memberEmail AND ");
		}

		if (member.getMemberId() != null && !member.getMemberId().isEmpty()) {
			nativeSql.append("MEMBER_ID LIKE :memberId AND ");
		}

		nativeSql.append("1 ORDER BY MEMBER_NO");

		System.out.println("member.getMemberNo():" + member.getMemberNo());
		System.out.println("member.getMemberAcct():" + member.getMemberAcct());
		System.out.println("member.getMemberPhone():" + member.getMemberPhone());
		System.out.println("member.getMemberEmail():" + member.getMemberEmail());
		System.out.println("member.getMemberId():" + member.getMemberId());
		System.out.println("nativeSql:" + nativeSql); // 驗證組合後的SQL指令

		Query<Member> query = getSession().createNativeQuery(nativeSql.toString(), Member.class);
		if (member.getMemberNo() != null) {
			query.setParameter("memberNo", "%" + member.getMemberNo() + "%");
		}
		if (member.getMemberAcct() != null && !member.getMemberAcct().isEmpty()) {
			query.setParameter("memberAcct", "%" + member.getMemberAcct() + "%");
		}
		if (member.getMemberPhone() != null && !member.getMemberPhone().isEmpty()) {
			query.setParameter("memberPhone", "%" + member.getMemberPhone() + "%");
		}
		if (member.getMemberEmail() != null && !member.getMemberEmail().isEmpty()) {
			query.setParameter("memberEmail", "%" + member.getMemberEmail() + "%");
		}
		if (member.getMemberId() != null && !member.getMemberId().isEmpty()) {
			query.setParameter("memberId", "%" + member.getMemberId() + "%");
		}

		return query.getResultList();
	}

	@Override
	public int updateWalletAmountByMemberNoAndWalletAmount(Integer memberNo, Double memberWalletAmount) {
		final String hql = "UPDATE Member SET memberWalletAmount = :memberWalletAmount WHERE memberNo = :memberNo";
		Query<?> query = getSession().createQuery(hql);
		return query
				.setParameter("memberNo", memberNo)
				.setParameter("memberWalletAmount", memberWalletAmount)
				.executeUpdate();
	}

	@Override
	public Member selectMemberByMemberNo(Integer memberNo) {
		final String hql = "FROM Member WHERE memberNo = :memberNo ORDER BY memberNo";
		return getSession()
				.createQuery(hql, Member.class)
				.setParameter("memberNo", memberNo)
				.uniqueResult();
	}
}
