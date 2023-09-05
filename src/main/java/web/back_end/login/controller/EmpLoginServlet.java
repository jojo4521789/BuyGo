package web.back_end.login.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static web.back_end.login.util.EmpLoginConstants.SERVICE;

import web.back_end.user_acc.acc.entity.Emp;

@WebServlet("api/back_end/EmpLogin")
public class EmpLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		Emp emp = json2Pojo(request, Emp.class);
		
		boolean b = SERVICE.checkEmpAcctPw(emp.getEmpAcct(), emp.getEmpPw());
		if (b) {
			HttpSession session = request.getSession();
			String sessionId = session.getId();
			Integer empNo = SERVICE.LoadEmpAcctPw(emp.getEmpAcct(), emp.getEmpPw()).getEmpNo();
			System.out.println("Login Success");
			
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("empNo", empNo);
			
			writePojo2Json(response, emp);
		} else {
			System.out.println("Login Fail");
			writePojo2Json(response, emp);
		}
		
	}
	
}
