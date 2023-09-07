package web.front_end.member.chat.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import web.front_end.member.acc.entity.Member;
import web.front_end.member.chat.entity.ChatMessage;
import web.front_end.member.chat.entity.State;
import web.front_end.member.chat.util.JedisHandleMessage;


@ServerEndpoint("/ChatRoomWS/{memberNo}")
public class ChatRoomWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>(); // 全域變數，保存目前所有上線者的Session，可使用Session發送websocket
	private static Map<String, Map<String, String>> totalOfCreateChatMap = new ConcurrentHashMap<>(); // 全域變數，供各函式調用時使用，保存所有登入者自己的memberNo(key),createChatMap(value)
	
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("memberNo") String memberNo, Session userSession) throws IOException {
		Map<String, String> createChatMap = new ConcurrentHashMap<String, String>(); // 用以保存該登入者聊天室左側的所有memberAcct
		/* save the new user in the map */
		sessionsMap.put(memberNo, userSession);
		Set<String> chatMemberNoList = JedisHandleMessage.getChatMemberNoList(memberNo); // 取得使用者過往有開啟過聊天室的對象memberNo
		List<String> memberDetailList =  JedisHandleMessage.getmemberDetailList(); // 取得Redis中memberNo和memberAcct對照的List
		
		for(String chatMemberNo : chatMemberNoList) { // 遍歷該登入者過往有開啟過聊天室的對象
//			System.out.println("chatMemberNo:" + chatMemberNo);
			for(String memberDetail : memberDetailList) { // 遍歷memberDetail
				Member member = gson.fromJson(memberDetail, Member.class); // 將Json轉為entity
				if(chatMemberNo.equals(Integer.toString(member.getMemberNo()))) { // 
//					System.out.println("member.getMemberNo():" + member.getMemberNo());
//					System.out.println("member.getMemberAcct():" + member.getMemberAcct());
					createChatMap.put(chatMemberNo, member.getMemberAcct()); // 依次迭代將目前登入者聊天室左側的所有memberAcct放入Map中
				}
			}
		}
		
		totalOfCreateChatMap.put(memberNo, createChatMap); // 將登入者的createChatMap存入全域的Map
		
//		for(String memberDetail : memberDetailList) {
//			Member member = gson.fromJson(memberDetail, Member.class); // 將Json轉為entity
//			System.out.println("memberDetail:" + memberDetail);
//			System.out.println("member.getMemberNo():" + member.getMemberNo());
//			System.out.println("member.getMemberAcct():" + member.getMemberAcct());
//		}
		
		//驗證createChatMap
//		System.out.println("----------");
//		for(String key : createChatMap.keySet()) {
//			System.out.println("key:" + key + ", " + "value:" + createChatMap.get(key));
//		}
//		System.out.println("----------");
		
		// 驗證sessionsMap
//		System.out.println("sessionsMap.size():" + sessionsMap.size());
//		System.out.println("------------");
//		for(String key : sessionsMap.keySet()) {
//			System.out.println("key:" + key + ", " + "value:" + sessionsMap.get(key));
//		}
//		System.out.println("------------");
		
		/* Sends all the connected users to the new user */
//		Set<String> memberNos = sessionsMap.keySet();
		
		// 驗證memberNo
//		System.out.println("memberNo:" + memberNo);
		// 驗證memberNos
//		System.out.println("------------");
//		System.out.println("memberNos.size():" + memberNos.size());
//		for(String permemberNos :memberNos) {
//			System.out.println("permemberNos:" + permemberNos);
//		}
//		System.out.println("------------");
		
		State stateMessage = new State("open", memberNo, createChatMap);
		String stateMessageJson = gson.toJson(stateMessage);
		// 驗證stateMessageJson
//		System.out.println("stateMessageJson:" + stateMessageJson);
//		Collection<Session> sessions = sessionsMap.values();
		
		// 驗證 sessions
//		System.out.println("sessions.size():" + sessions.size());
//		System.out.println("---------------");
//		for(Session session : sessions) {
//			System.out.println("session:" + session);
//		}
//		System.out.println("---------------");
		
		// 傳送已上線的會員目前所有上線會員的名單，於前端渲染左側好友列表
//		for (Session session : sessions) {
//			if (session.isOpen()) {
//				session.getAsyncRemote().sendText(stateMessageJson);
//			}
//		}
		
		userSession.getAsyncRemote().sendText(stateMessageJson); // 發送websocket至登入者頁面，以更新聊天室左側的memberAcct
		
//		String text = String.format("Session ID = %s, connected; memberNo = %s%nusers: %s", userSession.getId(),
//				memberNo, memberNos);
//		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();
		// 如果Type為history，將過往對話記錄傳至前端渲染頁面
		if ("history".equals(chatMessage.getType())) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			String historyMsg = gson.toJson(historyData);
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg);
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
				return;
			}
		}
		
		// 如果Type不為history(為chat)
		Session receiverSession = sessionsMap.get(receiver);
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(message);
		}
		userSession.getAsyncRemote().sendText(message);
		JedisHandleMessage.saveChatMessage(sender, receiver, message);

		boolean isCreatChatExist = JedisHandleMessage.isCreatChatExist(receiver, sender); // 確認對方聊天室是否存在
		if(!isCreatChatExist) { // 如果對方的聊天室不存在
			JedisHandleMessage.creatChatRoom(receiver, sender); // 創建一個對方的聊天室於Redis
//			System.out.println("已成功創建對方的聊天室");
			
			// 使用websocket動態建立對方的聊天室
			Session session = sessionsMap.get(receiver); // 取得對方的session，以傳送socket過去
			if (userSession != null && userSession.isOpen()) { // 確認對方當前式登入的狀態，如果是登入狀態，則以websocket直接在對方聊天室左方新增我方的會員帳號
				Map<String, String> map =  new ConcurrentHashMap<>();
				for(String totalOfCreateChatMapKey : totalOfCreateChatMap.keySet()) { // 遍歷所有登入者聊天室左方的會員名單
				 	Map<String, String> catchCreateChatMap = totalOfCreateChatMap.get(totalOfCreateChatMapKey);
				 	if(totalOfCreateChatMapKey.equals(receiver)) { // 如果遍歷到的totalOfCreateChatMapKey等於要查詢的memberNo(receiver)
						for(String catchCreateChatMapKey : catchCreateChatMap.keySet()) {
							map.put(catchCreateChatMapKey, catchCreateChatMap.get(catchCreateChatMapKey)); // 將該memberNo原先設定的聊天室左方會員名單設置進map
						}
				 	}
				}
				map.put(sender, JedisHandleMessage.getMemberAcctByMemberNo(sender)); // 於map再增加一筆自己的memberNo和memberAcct
				State stateMessage = new State("open", receiver, map);
				String stateMessageJson = gson.toJson(stateMessage);
				
				session.getAsyncRemote().sendText(stateMessageJson); // 傳送更新後的聊天室左方名單給對方的聊天室，進行即時更新
			}
		}
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String memberNoClose = null;
		Set<String> memberNos = sessionsMap.keySet();
		for (String memberNo : memberNos) {
			if (sessionsMap.get(memberNo).equals(userSession)) {
				memberNoClose = memberNo;
				sessionsMap.remove(memberNo);
				break;
			}
		}

//		if (memberNoClose != null) {
//			State stateMessage = new State("close", memberNoClose, createChatMap);
//			String stateMessageJson = gson.toJson(stateMessage);
//			Collection<Session> sessions = sessionsMap.values();
//			for (Session session : sessions) {
//				session.getAsyncRemote().sendText(stateMessageJson);
//			}
//		}

//		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
//				reason.getCloseCode().getCode(), memberNos);
//		System.out.println(text);
	}
}
