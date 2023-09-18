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

import web.front_end.member.black.entity.Blacklist;

@WebServlet("/needLoginApi/front_end/addBlacklist")
public class AddBlacklistServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // 取得當前請求的Session
	    response.setCharacterEncoding("UTF-8");
	    Blacklist blacklist = json2Pojo(request, Blacklist.class);
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
}
