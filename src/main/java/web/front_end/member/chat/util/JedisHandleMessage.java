package web.front_end.member.chat.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import web.front_end.member.acc.entity.Member;
import web.front_end.member.chat.entity.ChatMessage;

public class JedisHandleMessage {

	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static List<String> getHistoryMsg(String sender, String receiver) {
		String key = new StringBuilder("chatHistory*").append(sender).append(":").append(receiver).toString();
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.select(1); // 選擇db1
		List<String> historyData = jedis.lrange(key, 0, -1);
		jedis.close();
		// 驗證 historyData
//		System.out.println("historyData:" + historyData);
		return historyData;
	}

	public static void saveChatMessage(String sender, String receiver, String message) {
		// 對雙方來說，都要各存著歷史聊天記錄
		String senderKey = new StringBuilder("chatHistory*").append(sender).append(":").append(receiver).toString();
		String receiverKey = new StringBuilder("chatHistory*").append(receiver).append(":").append(sender).toString();
		Jedis jedis = pool.getResource();
		jedis.select(1); // 選擇db1
		jedis.rpush(senderKey, message);
		jedis.rpush(receiverKey, message);
//		System.out.println("成功設置聊天歷史記錄,senderKey:" + senderKey + " receiverKey:" + receiverKey);
		jedis.close();
	}

	public static Set<String> getChatMemberNoList(String memberNo) {
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.select(1); // 選擇db1
		Set<String> list = jedis.keys("*"); // 取得Redis所有key
		Set<String> newList = new HashSet<String>();
		for(String str : list) { // 遍歷所有key
			if(str.startsWith("createChat*")) { // 確認該key為createChat*開頭
				if(memberNo.equals(str.substring(str.indexOf("createChat*") + 11,str.indexOf(":")))) { // 比對登入者的memberNo和key左側的memberNo是否相等
					newList.add(str.substring(str.indexOf(":") + 1,str.length())); // 將使用者曾對話過，對方的memberNo存入newList
				}
			}
		}
		// 測試
//		System.out.println("memberNo:" + memberNo);
//		String keyName = "createChat*" + memberNo;
//		Set<String> chatMemberNoList = jedis.keys(keyName);
//		System.out.println("chatMemberNoList.size():" + chatMemberNoList.size());
//		System.out.println("-------------");
//		for(String str : chatMemberNoList) {
//			System.out.println("str:" + str);
//		}
//		System.out.println("-------------");
		
		return newList;
	}
	
	// 取得Redis中memberDetail的所有資料並回傳
	public static List<String> getmemberDetailList() {
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.select(1); // 選擇db1
		List<String> memberDetails = jedis.lrange("memberDetail", 0, -1);
		jedis.close();
		return memberDetails;
	}
	
	// 從Redis的memberDetail尋找memberNo匹配的memberAcct並回傳
	public static String getMemberAcctByMemberNo(String memberNo) {
		Gson gson = new Gson();
		
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.select(1); // 選擇db1
		List<String> memberDetails = jedis.lrange("memberDetail", 0, -1); // 取得所有的memberDetail
		jedis.close();
		
		for(String memberDetail : memberDetails) {
			Member memberDetailPojo = gson.fromJson(memberDetail, Member.class);
			if(memberNo.equals(Integer.toString(memberDetailPojo.getMemberNo()))) {
				return memberDetailPojo.getMemberAcct();
			}
			memberDetailPojo = null;
		}
		return ""; // 如果找不到，回傳空字串
	}
	
	public static boolean isCreatChatExist(String sender, String receiver) {
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.select(1); // 選擇db1
		Set<String> list = jedis.keys("*"); // 取得Redis所有key
		for(String str : list) { // 遍歷所有key
			if(str.startsWith("createChat*")) { // 確認該key為chatHistory*開頭
				if(sender.equals(str.substring(str.indexOf("createChat*") + 11,str.indexOf(":"))) && receiver.equals(str.substring(str.indexOf(":") + 1,str.length()))) {
					jedis.close();
					return true;
				}
			}
		}
		jedis.close();
		return false;
	}
	
	// 創建chatRoom，該資料可使sender方聊天室左方顯示receiver方的memberAcct
	public static void creatChatRoom(String sender, String receiver) {
		Gson gson = new Gson();
		Jedis jedis = pool.getResource();
		jedis.select(1); // 選擇db1
		String key = new StringBuilder("createChat*").append(sender).append(":").append(receiver).toString();
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setType("create");
		chatMessage.setSender(receiver);
		chatMessage.setReceiver(sender);
		String chatMessageToJson = gson.toJson(chatMessage, ChatMessage.class);
		jedis.rpush(key, chatMessageToJson);
		jedis.close();
	}
}
