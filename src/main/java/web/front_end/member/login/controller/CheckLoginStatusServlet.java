package web.front_end.member.login.controller;

import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.login.util.LoginConstants.SERVICE;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.acc.entity.Member;
import web.front_end.member.login.dto.LoginCheckDTO;

@WebServlet("/api/front_end/checkLoginStatus")
public class CheckLoginStatusServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(); // 取得當前請求的Session
		
		LoginCheckDTO loginCheckDTO = new LoginCheckDTO();
		if (session.getAttribute("sessionId") != null) {
			Member member = SERVICE.LoadMemberAcctByMemberNo((Integer)session.getAttribute("memberNo"));
			loginCheckDTO.setMemberNo(member.getMemberNo());
			loginCheckDTO.setMemberAcct(member.getMemberAcct());
			loginCheckDTO.setLoginState(true);
			writePojo2Json(response, loginCheckDTO);
		}
		else {
			loginCheckDTO.setLoginState(false);
			writePojo2Json(response, loginCheckDTO);
		}
	}
}
