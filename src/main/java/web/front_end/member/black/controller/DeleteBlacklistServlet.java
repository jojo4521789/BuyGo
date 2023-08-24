package web.front_end.member.black.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.black.util.BlacklistConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.black.entity.Blacklist;

@WebServlet("/needLoginApi/front_end/deleteBlacklist")
public class DeleteBlacklistServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
	response.setCharacterEncoding("UTF-8");
	HttpSession session = request.getSession(); // 取得當前請求的Session
	Blacklist blacklist = json2Pojo(request, Blacklist.class);
	
	
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
}
