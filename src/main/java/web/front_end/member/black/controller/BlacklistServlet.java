package web.front_end.member.black.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.black.util.BlacklistConstants.SERVICE;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import core.dto.ActionDTO;
import web.front_end.member.black.entity.Blacklist;

@WebServlet("/needLoginApi/front_end/blacklist")
public class BlacklistServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // 取得當前請求的Session
	    response.setCharacterEncoding("UTF-8");
	    
	    Gson gson = new Gson();
	    Object requestObj = json2Pojo(request, Object.class);
	    String requestToJson = gson.toJson(requestObj);
	    ActionDTO actionDTO = gson.fromJson(requestToJson, ActionDTO.class);
	    
	    String action = actionDTO.getAction();
	    // -----action共有以下模式-----
	    // addBlacklist 新增黑名單
	    // deleteBlacklist 刪除黑名單
	    // loadBlacklist 讀取黑名單
	    // ------------------------
	    if ("addBlacklist".equals(action)) {
		    Blacklist blacklist = gson.fromJson(requestToJson, Blacklist.class);
		    blacklist.setMemberNo((Integer)session.getAttribute("memberNo"));
		    // 查詢欲設定的黑名單是否已存在
		    List<Blacklist> checkList = SERVICE.loadByMemberNoAndMemberNoBlack(blacklist.getMemberNo(), blacklist.getMemberNoBlack());
			if(checkList.size() > 0) {
				blacklist.setMessage("已有重複的黑名單");
				blacklist.setSuccessful(false);
				writePojo2Json(response, blacklist);
				return;
			}
			// 新增黑名單進資料庫
			boolean isSuccess = SERVICE.addBlack(blacklist);
			if(isSuccess == false) {
				blacklist.setMessage("新增黑名單錯誤，請聯繫管理員");
				blacklist.setSuccessful(false);
				writePojo2Json(response, blacklist);
				return;
			}
		    
			blacklist.setSuccessful(true);
			writePojo2Json(response, blacklist);
	    }
	    
	    if ("deleteBlacklist".equals(action)) {
			Blacklist blacklist = gson.fromJson(requestToJson, Blacklist.class);
			
			// 如果刪除成功
			if(SERVICE.deleteBlacklistByMemberNoAndMemberNoBlack((Integer)(session.getAttribute("memberNo")), blacklist.getMemberNoBlack())) {
				blacklist.setSuccessful(true);
				writePojo2Json(response, blacklist);
			}
			else { // 目前尚無刪除失敗的回傳
				blacklist.setSuccessful(false);
				writePojo2Json(response, blacklist);
			}
	    }
	    
	    if ("loadBlacklist".equals(action)) {
			List<Blacklist> blacklists = SERVICE.loadBlacklistByMemberNo((Integer)(session.getAttribute("memberNo"))); // 依目前登入者的memberNo查詢黑名單
			if(blacklists.size() != 0) {
				writePojo2Json(response, blacklists);
			}
	    }
	}
}
