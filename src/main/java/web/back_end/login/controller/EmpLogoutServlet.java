package web.back_end.login.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EmpLoginApi/back_end/EmpLogout")
public class EmpLogoutServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String sessionId = (String)session.getAttribute("sessionId");
		Integer empno = (Integer)session.getAttribute("empno");
		System.out.println("使用者登出, empno:" + empno + ", session.getId:" + sessionId);
		
		session.invalidate();
		response.sendRedirect("/BuyGo/back_end/login.html");
		}
}
