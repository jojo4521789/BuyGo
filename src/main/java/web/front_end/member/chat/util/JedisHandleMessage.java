package web.front_end.member.chat.util;

import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import web.front_end.member.acc.entity.Member;
import web.front_end.member.chat.dto.UnreadMessageDTO;

public class JedisHandleMessage {

	private static int dbNum = 1; // 設定保存資料的Redis db號碼，聊天室相關操作都將使用此db保存與取值
	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static List<String> getHistoryMsg(String sender, String receiver) {
		String key = new StringBuilder("chatHistory_").append(sender).append(":").append(receiver).toString();
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.select(dbNum); // 選擇db
		List<String> historyData = jedis.lrange(key, 0, -1);
		jedis.close();
		return historyData;
	}

	public static void saveChatMessage(String sender, String receiver, String message) {
		// 對雙方來說，都要各存著歷史聊天記錄
		String senderKey = new StringBuilder("chatHistory_").append(sender).append(":").append(receiver).toString();
		String receiverKey = new StringBuilder("chatHistory_").append(receiver).append(":").append(sender).toString();
		Jedis jedis = pool.getResource();
		jedis.select(dbNum); // 選擇db
		jedis.rpush(senderKey, message);
		jedis.rpush(receiverKey, message);
//		System.out.println("成功設置聊天歷史記錄,senderKey:" + senderKey + " receiverKey:" + receiverKey);
		jedis.close();
	}

	// 回傳使用者曾對話過的memberNo
	public static Set<String> getChatMemberNoSet(String memberNo) {
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.select(dbNum); // 選擇db
		Set<String> chatMemberNoSet = jedis.smembers("createChat_" + memberNo);
		jedis.close();
		return chatMemberNoSet;
	}
	
	// 取得Redis中memberDetail的所有資料並回傳
	public static List<String> getmemberDetailList() {
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.select(dbNum); // 選擇db
		List<String> memberDetails = jedis.lrange("memberDetail", 0, -1);
		jedis.close();
		return memberDetails;
	}
	
	// 從Redis的memberDetail尋找memberNo匹配的memberAcct並回傳
	public static String getMemberAcctByMemberNo(String memberNo) {
		Gson gson = new Gson();
		
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.select(dbNum); // 選擇db
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
	
	public static boolean isCreatChatExist(String memberNo, String otherMemberNo) {
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.select(dbNum); // 選擇db
		Set<String> chatMemberNoSet = jedis.smembers("createChat_" + memberNo);
		for(String chatMemberNo : chatMemberNoSet) {
			if(chatMemberNo.equals(otherMemberNo)) {
//				System.out.println("找到存在的聊天室,memberNo:" + memberNo + " otherMemberNo:" + otherMemberNo);
				jedis.close();
				return true;
			}
		}
//		System.out.println("沒有找到聊天室,memberNo:" + memberNo + " otherMemberNo:" + otherMemberNo);
		jedis.close();
		return false;
	}
	
	// 創建chatRoom，該資料可使sender方聊天室左方顯示receiver方的memberAcct
	public static void creatChatRoom(String memberNo, String otherMemberNo) {
		Jedis jedis = pool.getResource();
		jedis.select(dbNum); // 選擇db
		String setKey = new StringBuilder("createChat_").append(memberNo).toString();
		jedis.sadd(setKey, otherMemberNo); // 將createChat資訊存入Redis，該帳號進入聊天室時，有createChat的帳號才會進入聊天室左側名單
		jedis.close();
	}
	
	public static void addCountForUnreadMsg(String memberNo, String otherMemberNo) {
		Gson gson = new Gson();
		Jedis jedis = pool.getResource();
		jedis.select(dbNum); // 選擇db
		String keyName = "unreadMsg_" + memberNo;
		
		List<String> list = jedis.lrange(keyName, 0, -1); // 取得Redis中key為目標字串的List
		String createKey = new StringBuilder("unreadMsg_").append(memberNo).toString();
		boolean isUnreadMsgExist = false; // 表示UnreadMsg是否存在
		for(String str : list) { // 遍歷list，看該list是否已有要遞增的UnreadMsg資料
			UnreadMessageDTO beforeAddCountUnreadMsg = gson.fromJson(str, UnreadMessageDTO.class);
			if(otherMemberNo.equals(beforeAddCountUnreadMsg.getOtherMemberNo())) {
				isUnreadMsgExist = true; // 如果UnreadMsg存在，將isUnreadMsgExist改為true
			}
		}
		if(!isUnreadMsgExist) { // 如果沒有創建過該memberNo的UnreadMsg在Redis
			UnreadMessageDTO unreadMessage = new UnreadMessageDTO(otherMemberNo, "1");
			String unreadMessageToJson = gson.toJson(unreadMessage, UnreadMessageDTO.class);
			jedis.rpush(createKey, unreadMessageToJson);
//			System.out.println("已創建UnreadMsg在Redis,MemberNo:" + memberNo + " otherMemberNo:" + otherMemberNo);
		}
		else { // 如果過去曾創建過該memberNo的UnreadMsg，對該UnreadMsg記數+1
			for(String unreadMsgToJson : list) {
				UnreadMessageDTO beforeAddCountUnreadMsg = gson.fromJson(unreadMsgToJson, UnreadMessageDTO.class);
				if(otherMemberNo.equals(beforeAddCountUnreadMsg.getOtherMemberNo())) {
					
					String beforeAddCountUnreadMessageToJson = gson.toJson(beforeAddCountUnreadMsg, UnreadMessageDTO.class);
					jedis.lrem(createKey, 0 , beforeAddCountUnreadMessageToJson); // 刪除原本的記錄
					
					Integer addCount = Integer.parseInt(beforeAddCountUnreadMsg.getNumOfMsg()) + 1;
					UnreadMessageDTO afterAddCountUnreadMessageDTO = new UnreadMessageDTO(otherMemberNo, Integer.toString(addCount));
					String afterAddCountUnreadMessageToJson = gson.toJson(afterAddCountUnreadMessageDTO, UnreadMessageDTO.class);
					jedis.rpush(createKey, afterAddCountUnreadMessageToJson); // 新增Count+1後的記錄
//					System.out.println("已將UnreadMsg + 1,MemberNo:" + memberNo + " otherMemberNo:" + otherMemberNo);
				}
			}
		}
		jedis.close();
	}
	
	public static String getUnreadMsgCountByMemberNoAndOtherMemberNo(String memberNo, String otherMemberNo) {
		Gson gson = new Gson();
		Jedis jedis = pool.getResource();
		jedis.select(dbNum); // 選擇db
		String keyName = "unreadMsg_" + memberNo;
		
		List<String> list = jedis.lrange(keyName, 0, -1);; // 取得Redis中key為目標字串的List
		jedis.close();
		for(String str : list) {
			UnreadMessageDTO unreadMsg = gson.fromJson(str, UnreadMessageDTO.class);
			if(otherMemberNo.equals(unreadMsg.getOtherMemberNo())) {
//				System.out.println("有找到UnreadMessage 使用者:" + memberNo + "對方:" + otherMemberNo + "訊息數:" + unreadMsg.getNumOfMsg());
				return unreadMsg.getNumOfMsg();
			}
			unreadMsg = null;
		}
//		System.out.println("沒找到UnreadMessage 使用者:" + memberNo + "對方:" + otherMemberNo);
		return "0"; // 未找到指定memberNo與otherMemberNo的UnreadMsg時，回傳0
	}
	
	public static void resetCountForUnreadMsg(String memberNo, String otherMemberNo) {
		Gson gson = new Gson();
		Jedis jedis = pool.getResource();
		jedis.select(dbNum); // 選擇db
		String key = "unreadMsg_" + memberNo;
		
		List<String> list = jedis.lrange(key, 0, -1); // 取得Redis中key為目標字串的List
		for(String str : list) {
			UnreadMessageDTO beforeResetCountUnreadMessageDTO = gson.fromJson(str, UnreadMessageDTO.class);
			if(otherMemberNo.equals(beforeResetCountUnreadMessageDTO.getOtherMemberNo())) {
				String beforeResetCountUnreadMessageDTOtoJson = gson.toJson(beforeResetCountUnreadMessageDTO, UnreadMessageDTO.class);
				jedis.lrem(key, 0 , beforeResetCountUnreadMessageDTOtoJson); // 刪除原本的記錄
//				System.out.println("已成功將使用者:" + memberNo + " 對方:" + otherMemberNo + " 未讀訊息刪除");
			}
		}
		
		UnreadMessageDTO afterResetCountUnreadMessageDTO = new UnreadMessageDTO(otherMemberNo, "0"); // 將未讀訊息數量改為0
		String afterResetCountUnreadMessageToJson = gson.toJson(afterResetCountUnreadMessageDTO, UnreadMessageDTO.class);
		jedis.rpush(key, afterResetCountUnreadMessageToJson); // 將重置的後的UnreadMessage資訊存到Redis
		jedis.close();
//		System.out.println("已成功將使用者:" + memberNo + " 對方:" + otherMemberNo + " 未讀訊息重置");
	}
}
