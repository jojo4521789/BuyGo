package web.front_end.member.chat.controller;

import static core.util.CommonUtil.json2Pojo;
import static web.front_end.member.login.util.LoginConstants.SERVICE;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import web.front_end.member.acc.entity.Member;
import web.front_end.member.chat.entity.ChatMessage;
import web.front_end.member.chat.util.JedisPoolUtil;

@WebServlet("/needLoginApi/front_end/addChatRoom")
public class AddChatRoomServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	Gson gson = new Gson();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(); // 取得當前請求的Session
		Member member= json2Pojo(request, Member.class);
		
		String setKey = new StringBuilder("createChat*").append((Integer)(session.getAttribute("memberNo"))).append(":").append(member.getMemberNo()).toString();
		Jedis jedis = pool.getResource();
		jedis.select(1); // 選擇db1
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setType("create");
		chatMessage.setSender(Integer.toString((Integer)session.getAttribute("memberNo")));
		chatMessage.setReceiver(member.getMemberNo().toString());
		String chatMessageToJson = gson.toJson(chatMessage, ChatMessage.class);
		jedis.rpush(setKey, chatMessageToJson); // 將createChat資訊存入Redis，該帳號進入聊天室時，有createChat的帳號才會進入聊天室左側名單
		
		boolean isMyMemberNoexist = false; // 自方memberNo是否存在於Redis的memberDetail
		boolean isOtherMemberNoexist = false; // 對方memberNo是否存在於Redis的memberDetail
		List<String> memberDetailListJson = jedis.lrange("memberDetail", 0, -1); // 取得Redis所有的memberDetail
		for(String str : memberDetailListJson) { // 遍歷所有memberDetail，如果要創建聊天室的自方或對方不存在此memberDetail中，則創建一筆進去
			Member memberDetail = gson.fromJson(str, Member.class);
//			System.out.println("memberDetail.getMemberNo():" + memberDetail.getMemberNo());
//			System.out.println("memberDetail.getMemberAcct():" + memberDetail.getMemberAcct());
			if(((Integer)session.getAttribute("memberNo")).equals(memberDetail.getMemberNo())) { // 如果要創建聊天室的自方存在此memberDetail中
//				System.out.println("登入者會員帳號存在於memberDetail*************");
				isMyMemberNoexist = true;
			}
//			else {
//				System.out.println("-------------");
//				System.out.println("登入者會員帳號不存在於memberDetail");
//				System.out.println("(Integer)(session.getAttribute(\"memberNo\")):" + (Integer)(session.getAttribute("memberNo")));
//				System.out.println("memberDetail.getMemberNo():" + memberDetail.getMemberNo());
//				System.out.println("-------------");
//			}
			if(member.getMemberNo() == memberDetail.getMemberNo()) { // 如果要創建聊天室的對方存在此memberDetail中
//				System.out.println("欲創建聊天室的會員帳號存在於memberDetail*************");
				isOtherMemberNoexist = true;
			}
//			else {
//				System.out.println("-------------");
//				System.out.println("欲創建聊天室的會員帳號不存在於memberDetail");
//				System.out.println("member.getMemberNo():" + member.getMemberNo());
//				System.out.println("memberDetail.getMemberNo():" + memberDetail.getMemberNo());
//				System.out.println("-------------");
//			}
		}
		if(!isMyMemberNoexist) {
			Member myMember = SERVICE.LoadMemberAcctByMemberNo((Integer)session.getAttribute("memberNo"));
			Member newMyMember = new Member();
			newMyMember.setMemberNo(myMember.getMemberNo());
			newMyMember.setMemberAcct(myMember.getMemberAcct());
			newMyMember.setSuccessful(true);
			String myMemberToJson = gson.toJson(newMyMember, Member.class);
			jedis.rpush("memberDetail", myMemberToJson);
			System.out.println("已建立自身memberNo至Detail");
		}
		if(!isOtherMemberNoexist) {
			Member otherMember = SERVICE.LoadMemberAcctByMemberNo(member.getMemberNo());
			Member newOtherMember = new Member();
			newOtherMember.setMemberNo(otherMember.getMemberNo());
			newOtherMember.setMemberAcct(otherMember.getMemberAcct());
			newOtherMember.setSuccessful(true);
			String otherMemberToJson = gson.toJson(newOtherMember, Member.class);
			jedis.rpush("memberDetail", otherMemberToJson);
			System.out.println("已建立對方memberNo至Detail");
		}
		jedis.close();
	}
}
