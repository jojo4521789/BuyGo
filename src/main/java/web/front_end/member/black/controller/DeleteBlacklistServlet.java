package web.front_end.member.black.controller;

import static core.util.CommonUtil.json2Pojo;
import static web.front_end.member.black.util.BlacklistConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.black.entity.Blacklist;

@WebServlet("/front_end/member/black/DeleteBlacklist")
public class DeleteBlacklistServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
	response.setCharacterEncoding("UTF-8");
	Blacklist blacklist = json2Pojo(request, Blacklist.class);
	
	// 如果刪除成功
	if(SERVICE.deleteBlacklistByMemberNoAndMemberNoBlack(blacklist.getMemberNo(), blacklist.getMemberNoBlack())) {
//		System.out.println("刪除成功");
	}
	else {
//		System.out.println("刪除失敗");
	}
	}
}
