package web.front_end.member.black.controller;

import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.black.util.BlacklistConstants.SERVICE;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.black.entity.Blacklist;
import web.front_end.member.login.dto.LoginCheckDTO;

@WebServlet("/needLoginApi/front_end/loadBlacklist")
public class LoadBlacklistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // 取得當前請求的Session

		response.setCharacterEncoding("UTF-8");

		List<Blacklist> blacklists = SERVICE.loadBlacklistByMemberNo((Integer)(session.getAttribute("memberNo"))); // 依目前登入者的memberNo查詢黑名單
		if(blacklists.size() != 0) {
			writePojo2Json(response, blacklists);
		}
	}
}
