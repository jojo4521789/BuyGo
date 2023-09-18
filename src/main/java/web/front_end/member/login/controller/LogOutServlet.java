package web.front_end.member.login.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/api/front_end/logOut")
public class LogOutServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(); // 取得當前請求的Session
		
		// 顯示登出者名稱
		String sessionId = (String)session.getAttribute("sessionId");
		Integer memberNo = (Integer)session.getAttribute("memberNo");
		System.out.println("使用者登出, memberNo:" + memberNo + ", session.getId:" + sessionId);
		
		session.invalidate(); // 完全銷毀session
		response.sendRedirect("/BuyGo/front_end/index.html"); // 重導至主頁面
	}
}
