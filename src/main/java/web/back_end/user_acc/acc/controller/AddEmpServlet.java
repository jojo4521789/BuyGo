package web.back_end.user_acc.acc.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.user_acc.acc.util.EmpConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.user_acc.acc.entity.Emp;

@WebServlet("/EmpLoginApi/back_end/AddEmpServlet")
public class AddEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		Emp emp = json2Pojo(request, Emp.class);
		
		SERVICE.insert(emp);
		System.out.println("emp.isSuccessful():" + emp.isSuccessful());
		if(emp.isSuccessful()) {
			System.out.println("修改成功");
			writePojo2Json(response, emp);
		}else {
			System.out.println("修改失敗");
			writePojo2Json(response, emp);
		}
	}

}
