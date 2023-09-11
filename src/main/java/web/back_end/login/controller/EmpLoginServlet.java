package web.back_end.login.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.login.util.EmpLoginConstants.SERVICE;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.back_end.user_acc.acc.entity.Emp;

@WebServlet("/api/back_end/EmpLogin")
public class EmpLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		Emp emp = json2Pojo(request, Emp.class);
//		EmpFun empFun = json2Pojo(request, EmpFun.class);
		
		boolean b = SERVICE.checkEmpAcctPw(emp.getEmpAcct(), emp.getEmpPw());
		emp.setSuccessful(b);
		if (b) {
			HttpSession session = request.getSession();
			String sessionId = session.getId();
			Integer empNo = SERVICE.LoadEmpAcctPw(emp.getEmpAcct(), emp.getEmpPw()).getEmpNo();
//			Integer funNo = SERVICE.LoadEmpFun(empFun.getEmpNO(), empFun.getFunNo()).getFunNo();
			Integer funNo = SERVICE.LoadEmpAcctPw(emp.getEmpAcct(), emp.getEmpPw()).getEmpfun();
			System.out.println("使用者登入成功, empNo:" + empNo + ", funNo:" + funNo + ", session.getId:" + sessionId);
			
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("empNo", empNo);
			session.setAttribute("funNo", funNo);
			
			writePojo2Json(response, emp);
		} else {
			System.out.println("Login Fail");
			writePojo2Json(response, emp);
		}
		
	}
	
}
