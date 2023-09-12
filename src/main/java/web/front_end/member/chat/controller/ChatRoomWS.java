package web.front_end.member.chat.controller;

import java.io.IOException;
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
import web.front_end.member.chat.dto.ChatMessageDTO;
import web.front_end.member.chat.dto.StateDTO;
import web.front_end.member.chat.util.JedisHandleMessage;

@ServerEndpoint("/ChatRoomWS/{memberNo}")
public class ChatRoomWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>(); // 全域變數，保存目前所有上線者的Session，可使用Session發送websocket
	private static Map<String, Map<String, String[]>> totalOfCreateChatMap = new ConcurrentHashMap<>(); // 全域變數，供各函式調用時使用，保存所有登入者自己的memberNo(key),createChatMap(value)
	
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("memberNo") String memberNo, Session userSession) throws IOException {
		Map<String, String[]> createChatMap = new ConcurrentHashMap<String, String[]>(); // 用以保存該登入者聊天室左側的所有memberAcct
		sessionsMap.put(memberNo, userSession); //
		Set<String> chatMemberNoList = JedisHandleMessage.getChatMemberNoSet(memberNo); // 取得使用者過往有開啟過聊天室的對象memberNo
		List<String> memberDetailList =  JedisHandleMessage.getmemberDetailList(); // 取得Redis中memberNo和memberAcct對照的List
		
		for(String chatMemberNo : chatMemberNoList) { // 遍歷該登入者過往有開啟過聊天室的對象
			for(String memberDetail : memberDetailList) { // 遍歷Redis中所有會員的memberDetail
				Member member = gson.fromJson(memberDetail, Member.class); // 將memberDetail Json轉為entity
				if(chatMemberNo.equals(Integer.toString(member.getMemberNo()))) { // 如果遍歷到的聊天室對象等於遍歷到的memberDetail
					
					String getUnreadMsgCount = JedisHandleMessage.getUnreadMsgCountByMemberNoAndOtherMemberNo(memberNo, Integer.toString(member.getMemberNo())); // 取得指定對象的未讀訊息數量
//					System.out.println("未讀訊息:" + getUnreadMsgCount + " 使用者:" + memberNo + " 對方:" + member.getMemberNo());
					
					String[] memberAcctAndUnreadMsg = {member.getMemberAcct(), getUnreadMsgCount};
					createChatMap.put(chatMemberNo, memberAcctAndUnreadMsg); // 依次迭代將目前登入者聊天室左側的所有memberAcct放入Map中
				}
			}
		}
		
		totalOfCreateChatMap.put(memberNo, createChatMap); // 將登入者的createChatMap存入全域的Map
		
		StateDTO stateMessage = new StateDTO("open", memberNo, createChatMap);
		String stateMessageJson = gson.toJson(stateMessage);
		
		userSession.getAsyncRemote().sendText(stateMessageJson); // 發送websocket至登入者頁面，以更新聊天室左側的memberAcct
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatMessageDTO chatMessage = gson.fromJson(message, ChatMessageDTO.class);
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();
		// 如果Type為history，將過往對話記錄傳至前端渲染頁面
		if ("history".equals(chatMessage.getType())) {
			JedisHandleMessage.resetCountForUnreadMsg(sender, receiver); // 重置未讀訊息的計數
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			String historyMsg = gson.toJson(historyData);
			
			ChatMessageDTO cmHistory = new ChatMessageDTO();
			cmHistory.setType("history");
			cmHistory.setSender(sender);
			cmHistory.setReceiver(receiver);
			cmHistory.setMessage(historyMsg);
			
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
			}
			return;
		}
		// 若前端使用者於當前對話對象切換到其他人時，須將當前對話對象的未讀計數重置
		else if("resetUnreadMsg".equals(chatMessage.getType())){
			JedisHandleMessage.resetCountForUnreadMsg(sender, receiver);
			return;
		}
		
		// 如果Type不為history或resetUnreadMsg(為chat)
		Session receiverSession = sessionsMap.get(receiver);
		JedisHandleMessage.addCountForUnreadMsg(receiver, sender); // 在對方的未讀訊息計數+1，並保存在Redis
		
		userSession.getAsyncRemote().sendText(message); // 在自己的聊天室即時傳送message
		JedisHandleMessage.saveChatMessage(sender, receiver, message); // 保存聊天紀錄
		
		if (receiverSession != null && receiverSession.isOpen()) { // 如果對方有上線
			ChatMessageDTO receiverChatMessage = gson.fromJson(message, ChatMessageDTO.class);
			String unreadMsgCount = JedisHandleMessage.getUnreadMsgCountByMemberNoAndOtherMemberNo(receiver, sender); // 取得對方的未讀訊息數
			receiverChatMessage.setUnreadMsgCount(unreadMsgCount);
			String receiverChatMessageToJson = gson.toJson(receiverChatMessage);
			
			receiverSession.getAsyncRemote().sendText(receiverChatMessageToJson); // 在對方的聊天室即時傳送message
		}

		boolean isCreatChatExist = JedisHandleMessage.isCreatChatExist(receiver, sender); // 確認對方聊天室是否存在
		if(!isCreatChatExist) { // 如果對方的聊天室不存在
			JedisHandleMessage.creatChatRoom(receiver, sender); // 創建一個對方的聊天室於Redis
//			System.out.println("已成功創建對方的聊天室");
			
			// 使用websocket動態建立對方的聊天室
			if (receiverSession != null && receiverSession.isOpen()) { // 確認對方當前式登入的狀態，如果是登入狀態，則以websocket直接在對方聊天室左方新增我方的會員帳號
				Session session = sessionsMap.get(receiver); // 取得對方的session，以傳送socket過去
				Map<String, String[]> map =  new ConcurrentHashMap<>();
				for(String totalOfCreateChatMapKey : totalOfCreateChatMap.keySet()) { // 遍歷所有登入者聊天室左方的會員名單
				 	Map<String, String[]> catchCreateChatMap = totalOfCreateChatMap.get(totalOfCreateChatMapKey);
				 	if(totalOfCreateChatMapKey.equals(receiver)) { // 如果遍歷到的totalOfCreateChatMapKey等於要查詢的memberNo(receiver)
						for(String catchCreateChatMapKey : catchCreateChatMap.keySet()) {
							map.put(catchCreateChatMapKey, catchCreateChatMap.get(catchCreateChatMapKey)); // 將該memberNo原先設定的聊天室左方會員名單設置進map
						}
				 	}
				}
				String[] memberAcctAndUnreadMsg = {JedisHandleMessage.getMemberAcctByMemberNo(sender), ""};
				map.put(sender, memberAcctAndUnreadMsg); // 於map再增加一筆自己的memberNo和memberAcct
				StateDTO stateMessage = new StateDTO("open", receiver, map);
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
		Set<String> memberNos = sessionsMap.keySet();
		for (String memberNo : memberNos) {
			if (sessionsMap.get(memberNo).equals(userSession)) {
				sessionsMap.remove(memberNo);
				break;
			}
		}
	}
}
