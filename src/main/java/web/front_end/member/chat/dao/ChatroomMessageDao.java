package web.front_end.member.chat.dao;

import java.util.List;

import core.dao.CoreDao;
import web.front_end.member.black.entity.Blacklist;
import web.front_end.member.chat.entity.ChatroomMessage;
import web.front_end.seller.gpa.prod.entity.GpaProd;

public interface ChatroomMessageDao extends CoreDao<ChatroomMessage, Integer> {
	List<ChatroomMessage> selectByMemberNoSendAndMemberNoRec(Integer memberNoSend, Integer memberNoRec);
}
