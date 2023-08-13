package web.front_end.member.chat.service.impl;

import java.util.List;

import web.front_end.member.chat.dao.ChatroomMessageDao;
import web.front_end.member.chat.dao.impl.ChatroomMessageDaoImpl;
import web.front_end.member.chat.entity.ChatroomMessage;
import web.front_end.member.chat.service.ChatService;

public class ChatServiceImpl implements ChatService{
	private ChatroomMessageDao dao;
	
	public ChatServiceImpl() {
		dao = new ChatroomMessageDaoImpl();
	}

	@Override
	public boolean saveMsg(ChatroomMessage chatroomMessage) {
		return dao.insert(chatroomMessage) > 0;
	}

	@Override
	public List<ChatroomMessage> loadMsgByMemberNoSendAndMemberNoRec(Integer memberNoSend, Integer memberNoRec) {
		return dao.selectByMemberNoSendAndMemberNoRec(memberNoSend, memberNoRec);
	}
}
