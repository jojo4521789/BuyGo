package web.front_end.member.chat.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import web.front_end.member.black.dao.impl.BlacklistDaoImpl;
import web.front_end.member.black.entity.Blacklist;
import web.front_end.member.chat.dao.ChatroomMessageDao;
import web.front_end.member.chat.entity.ChatroomMessage;
import web.front_end.seller.gpa.prod.entity.GpaProd;

public class ChatroomMessageDaoImpl implements ChatroomMessageDao{

	public static void main(String[] args) {
		ChatroomMessageDaoImpl chatroomMessageDaoImpl = new ChatroomMessageDaoImpl();
		
		// 測試
		// 新增insert
//		Session session = chatroomMessageDaoImpl.getSession();
//		
//		ChatroomMessage chatroomMessage = new ChatroomMessage();
//		chatroomMessage.setMemberNoSend(6);
//		chatroomMessage.setMemberNoRec(4);
//		chatroomMessage.setMessage("安安");
//		
//		try {
//			Transaction transaction = session.beginTransaction(); // 開始交易
//			chatroomMessageDaoImpl.insert(chatroomMessage); // 新增
//			transaction.commit(); // 送交，同時會結束交易
//			System.out.println("成功");
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback(); // 還原，同時會結束交易
//			System.out.println("失敗");
//		}
		
		//刪除deleteById
//		Session session = chatroomMessageDaoImpl.getSession();
//		
//		try {
//			Transaction transaction = session.beginTransaction(); // 開始交易
//			chatroomMessageDaoImpl.deleteById(5); // 刪除
//			transaction.commit(); // 送交，同時會結束交易
//			System.out.println("成功");
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback(); // 還原，同時會結束交易
//			System.out.println("失敗");
//		}
		
		// 修改update
//		Session session = chatroomMessageDaoImpl.getSession();
//		
//		ChatroomMessage chatroomMessage = new ChatroomMessage();
//		chatroomMessage.setMessageNo(6);
//		chatroomMessage.setMemberNoSend(5);
//		chatroomMessage.setMemberNoRec(3);
//		chatroomMessage.setMessage("嗨嗨");
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		System.out.println(chatroomMessageDaoImpl.update(chatroomMessage));
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectById
//		Session session = chatroomMessageDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		System.out.println(chatroomMessageDaoImpl.selectById(3));
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectAll
//		Session session = chatroomMessageDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<ChatroomMessage> chatroomMessagesList = chatroomMessageDaoImpl.selectAll();
//		for (ChatroomMessage chatroomMessages : chatroomMessagesList) {
//			System.out.print("MessageNo:" + chatroomMessages.getMessageNo() + ",");
//			System.out.print("MemberNoSend:" + chatroomMessages.getMemberNoSend() + ",");
//			System.out.print("MemberNoSend:" + chatroomMessages.getMemberNoSend() + ",");
//			System.out.println("Message:" + chatroomMessages.getMessage());
//		}
//		transaction.commit(); // 送交，同時會結束交易
		
		// 查詢selectByMemberNoSendAndMemberNoRec
//		Session session = chatroomMessageDaoImpl.getSession();
//		
//		Transaction transaction = session.beginTransaction(); // 開始交易
//		List<ChatroomMessage> chatroomMessageList = chatroomMessageDaoImpl.selectByMemberNoSendAndMemberNoRec(1,2);
//		for (ChatroomMessage chatroomMessage : chatroomMessageList) {
//			System.out.print("messageNo:" + chatroomMessage.getMessageNo() + ",");
//			System.out.print("memberNoSend:" + chatroomMessage.getMemberNoSend() + ",");
//			System.out.print("memberNoRec:" + chatroomMessage.getMemberNoRec() + ",");
//			System.out.print("message:" + chatroomMessage.getMessage() + ",");
//			System.out.println("messageTime:" + chatroomMessage.getMessageTime());
//		}
//		transaction.commit(); // 送交，同時會結束交易
	}
	
	@Override
	public int insert(ChatroomMessage chatroomMessage) {
		getSession().persist(chatroomMessage);
		return 1;
	}
	
	@Override
	public int deleteById(Integer id) {
		Session session = getSession();
		ChatroomMessage chatroomMessage = session.get(ChatroomMessage.class, id);
		session.remove(chatroomMessage);
		return 1;
	}

	@Override
	public int update(ChatroomMessage entity) {
		final String hql = "UPDATE ChatroomMessage SET memberNoSend = :memberNoSend, memberNoRec = :memberNoRec, message = :message WHERE messageNo = :messageNo";
		Query<?> query = getSession().createQuery(hql);
		return query
				.setParameter("memberNoSend", entity.getMemberNoSend())
				.setParameter("memberNoRec", entity.getMemberNoRec())
				.setParameter("message", entity.getMessage())
				.setParameter("messageNo", entity.getMessageNo())
				.executeUpdate();
	}

	@Override
	public ChatroomMessage selectById(Integer id) {
		return getSession().get(ChatroomMessage.class, id);
	}

	@Override
	public List<ChatroomMessage> selectAll() {
		final String hql = "FROM ChatroomMessage ORDER BY messageNo";
		return getSession()
				.createQuery(hql, ChatroomMessage.class)
				.getResultList();
	}

	@Override
	public List<ChatroomMessage> selectByMemberNoSendAndMemberNoRec(Integer memberNoSend, Integer memberNoRec){
		final String hql = "FROM ChatroomMessage WHERE memberNoSend = :memberNoSend and memberNoRec = :memberNoRec ORDER BY messageNo";
		return getSession()
				.createQuery(hql, ChatroomMessage.class)
				.setParameter("memberNoSend", memberNoSend)
				.setParameter("memberNoRec", memberNoRec)
				.getResultList();
	}
	
}
