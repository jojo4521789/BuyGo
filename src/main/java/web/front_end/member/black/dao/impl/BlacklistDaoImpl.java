package web.front_end.member.black.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.front_end.member.black.dao.BlacklistDao;
import web.front_end.member.black.entity.Blacklist;

public class BlacklistDaoImpl implements BlacklistDao{
	
	public static void main(String[] args) {
		BlacklistDaoImpl blacklistDaoImpl = new BlacklistDaoImpl();
		
		// 測試
		// 新增insert
//		Session session = blacklistDaoImpl.getSession();
//		
//		Blacklist blacklist = new Blacklist();
//		blacklist.setMemberNo(5);
//		blacklist.setMemberNoBlack(3);
//		
//		try {
//			Transaction transaction = session.beginTransaction(); // 開始交易
//			blacklistDaoImpl.insert(blacklist); // 新增
//			transaction.commit(); // 送交，同時會結束交易
//			System.out.println("成功");
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback(); // 還原，同時會結束交易
//			System.out.println("失敗");
//		}
		
		//刪除deleteById
//		Session session = blacklistDaoImpl.getSession();
//		
//		try {
//			Transaction transaction = session.beginTransaction(); // 開始交易
//			blacklistDaoImpl.deleteById(8); // 刪除
//			transaction.commit(); // 送交，同時會結束交易
//			System.out.println("成功");
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback(); // 還原，同時會結束交易
//			System.out.println("失敗");
//		}
		
		// 修改update
//		Session session = blacklistDaoImpl.getSession();
//		
//		Blacklist blacklist = new Blacklist();
//		blacklist.setBlackNo(4);
//		blacklist.setMemberNo(5);
//		blacklist.setMemberNoBlack(3);
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		System.out.println(blacklistDaoImpl.update(blacklist));
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectById
//		Session session = blacklistDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		System.out.println(blacklistDaoImpl.selectById(3));
//		transaction.commit(); // 送交，同時會結束交易
//		
		// 查詢selectAll
//		Session session = blacklistDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<Blacklist> blacklistList = blacklistDaoImpl.selectAll();
//		for (Blacklist blacklist : blacklistList) {
//			System.out.print("BlackNo:" + blacklist.getBlackNo() + ",");
//			System.out.print("MemberNo:" + blacklist.getMemberNo() + ",");
//			System.out.println("MemberNoBlack:" + blacklist.getMemberNoBlack());
//		}
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectByMemberNo
//		Session session = blacklistDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<Blacklist> blacklistList = blacklistDaoImpl.selectByMemberNo(1);
//		for (Blacklist blacklist : blacklistList) {
//			System.out.print("BlackNo:" + blacklist.getBlackNo() + ",");
//			System.out.print("MemberNo:" + blacklist.getMemberNo() + ",");
//			System.out.println("MemberNoBlack:" + blacklist.getMemberNoBlack());
//		}
//		transaction.commit(); // 送交，同時會結束交易
		
		// 刪除deleteByMemberNoAndMemberNoBlack
//		Session session = blacklistDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		System.out.println(blacklistDaoImpl.deleteByMemberNoAndMemberNoBlack(5,2));
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectByMemberNoAndMemberNoBlack
//		Session session = blacklistDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<Blacklist> blacklistList = blacklistDaoImpl.selectByMemberNoAndMemberNoBlack(1, 9);
//		for (Blacklist blacklist : blacklistList) {
//			System.out.print("BlackNo:" + blacklist.getBlackNo() + ",");
//			System.out.print("MemberNo:" + blacklist.getMemberNo() + ",");
//			System.out.println("MemberNoBlack:" + blacklist.getMemberNoBlack());
//		}
//		System.out.println("blacklistList.size:" + blacklistList.size());
//		transaction.commit(); // 送交，同時會結束交易
	}
	
	@Override
	public int insert(Blacklist blacklist) {
		getSession().persist(blacklist);
		return 1;
	}
	
	@Override
	public int deleteById(Integer id) {
		Session session = getSession();
		Blacklist blacklist = session.get(Blacklist.class, id);
		session.remove(blacklist);
		return 1;
	}

	@Override
	public int update(Blacklist entity) {
		final String hql = "UPDATE Blacklist SET memberNo = :memberNo,memberNoBlack = :memberNoBlack WHERE blackNo = :blackNo";
		Query<?> query = getSession().createQuery(hql);
		return query
				.setParameter("memberNo", entity.getMemberNo())
				.setParameter("memberNoBlack", entity.getMemberNoBlack())
				.setParameter("blackNo", entity.getBlackNo())
				.executeUpdate();
	}

	@Override
	public Blacklist selectById(Integer id) {
		return getSession().get(Blacklist.class, id);
	}

	@Override
	public List<Blacklist> selectAll() {
		final String hql = "FROM Blacklist ORDER BY blackNo";
		return getSession()
				.createQuery(hql, Blacklist.class)
				.getResultList();
	}
	
	@Override
	public List<Blacklist> selectByMemberNo(Integer memberNo) {
		final String hql = "FROM Blacklist WHERE memberNo = :memberNo ORDER BY blackNo";
		return getSession()
				.createQuery(hql, Blacklist.class)
				.setParameter("memberNo", memberNo)
				.getResultList();
	}
	
	@Override
	public int deleteByMemberNoAndMemberNoBlack(Integer memberNo, Integer memberNoBlack) {
		final String hql = "DELETE Blacklist WHERE memberNo = :memberNo and memberNoBlack = :memberNoBlack";
		getSession()
		.createQuery(hql)
		.setParameter("memberNo", memberNo)
		.setParameter("memberNoBlack", memberNoBlack)
		.executeUpdate();
		return 1;
	}
	
	@Override
	public List<Blacklist> selectByMemberNoAndMemberNoBlack(Integer memberNo, Integer memberNoBlack) {
		final String hql = "FROM Blacklist WHERE memberNo = :memberNo AND memberNoBlack = :memberNoBlack ORDER BY blackNo";
		return getSession()
				.createQuery(hql, Blacklist.class)
				.setParameter("memberNo", memberNo)
				.setParameter("memberNoBlack", memberNoBlack)
				.getResultList();
	}
}
