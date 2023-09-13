package web.back_end.user_acc.acc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.user_acc.acc.entity.Emp;

import static web.back_end.user_acc.acc.util.EmpConstants.SERVICE;
import static core.util.CommonUtil.writePojo2Json;
import static core.util.CommonUtil.json2Pojo;

@WebServlet("/EmpLoginApi/back_end/LoadEmpServlet")
public class LoadEmpServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		
		final Integer empno = json2Pojo(request, Emp.class).getEmpNo();
		
		List<Emp> emps = SERVICE.selectEmpByNO(empno);
		writePojo2Json(response, emps);
	}
	
	
}
