package web.front_end.member.chat.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.chat.entity.ChatroomMessage;

public interface ChatService extends CoreService{
	boolean saveMsg(ChatroomMessage chatroomMessage);
	List<ChatroomMessage> loadMsgByMemberNoSendAndMemberNoRec(Integer memberNoSend, Integer memberNoRec);
}
