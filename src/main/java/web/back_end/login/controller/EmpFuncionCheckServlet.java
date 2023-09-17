package web.back_end.login.controller;

import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.login.util.EmpLoginConstants.SERVICE;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.back_end.login.dto.EmpFunctionCheckDTO;
import web.back_end.user_acc.acc.entity.Emp;

@WebServlet("/EmpLoginApi/back_end/EmpFunCheck")
public class EmpFuncionCheckServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		EmpFunctionCheckDTO empFunctionCheckDTO = new EmpFunctionCheckDTO();
		if (session.getAttribute("sessionId") != null) {
			Emp emp = SERVICE.LoadEmpByEmpNo((Integer)session.getAttribute("empNo"));
			empFunctionCheckDTO.setEmpNo(emp.getEmpNo());
			empFunctionCheckDTO.setEmpAcct(emp.getEmpAcct());
			empFunctionCheckDTO.setEmpFun(emp.getEmpfun());
			empFunctionCheckDTO.setLoginState(true);
			writePojo2Json(response, empFunctionCheckDTO);
			
		} else {
			empFunctionCheckDTO.setLoginState(false);
			writePojo2Json(response, empFunctionCheckDTO);
		}
	}
	
}
