package web.front_end.member.login.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.login.util.LoginConstants.SERVICE;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.acc.entity.Member;
import web.front_end.member.login.dto.LoginCheckDTO;
import web.front_end.member.login.dto.ResetPwDTO;

@WebServlet("/api/front_end/resetPw")
public class ResetPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(); // 取得當前請求的Session
		ResetPwDTO resetPwDTO = json2Pojo(request, ResetPwDTO.class);
		String memberAcct = (String)((Map)session.getAttribute("resetMemberMap")).get("memberAcct");
		String memberPw = resetPwDTO.getMemberPw();
		boolean isResetSuccess = SERVICE.ResetMemberPwByMemberAcct(memberAcct, memberPw);
		if(isResetSuccess) { // 修改密碼成功
			resetPwDTO.setSuccessful(true);
			writePojo2Json(response, resetPwDTO);
//			System.out.println("修改密碼成功");
		}
		else { // 修改密碼失敗
			resetPwDTO.setSuccessful(false);
			writePojo2Json(response, resetPwDTO);
//			System.out.println("修改密碼失敗");
		}
	}
}
