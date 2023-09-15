package web.back_end.user_acc.acc.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.back_end.user_acc.acc.entity.Emp;
import static web.back_end.login.util.EmpLoginConstants.SERVICE;
import static core.util.CommonUtil.writePojo2Json;


@WebServlet("/EmpLoginApi/back_end/LoadEmpServlet")
public class LoadEmpServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
//		final Integer empno = json2Pojo(request, Emp.class).getEmpNo();
//		
//		List<Emp> emps = SERVICE.selectEmpByNO(empno);
//		writePojo2Json(response, emps);
//		Emp emp = new Emp();
		if (session.getAttribute("sessionId") != null) {
		Emp emp = SERVICE.LoadEmpByEmpNo((Integer)session.getAttribute("empNo"));
		emp.setEmpAcct(emp.getEmpAcct());
		emp.setEmpPw(emp.getEmpPw());
		emp.setEmpName(emp.getEmpName());
		emp.setEmpGender(emp.getEmpGender());
		emp.setEmpTel(emp.getEmpTel());
		emp.setEmpMail(emp.getEmpMail());
		emp.setEmpfun(emp.getEmpfun());
		writePojo2Json(response, emp);
		}
	}
	
	
}
