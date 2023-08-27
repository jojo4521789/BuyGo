package web.front_end.member.login.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.login.util.LoginConstants.SERVICE;

import java.io.IOException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.acc.entity.Member;
import web.front_end.member.login.dto.LoginCheckDTO;
import web.front_end.member.login.dto.ResetPwDTO;

@WebServlet("/api/front_end/checkResetPwAuthCode")
public class CheckResetPwAuthCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(); // 取得當前請求的Session
		ResetPwDTO resetPwDTO = json2Pojo(request, ResetPwDTO.class);
		
		String authCode = (String)((Map)session.getAttribute("resetMemberMap")).get("authCode"); // 取得session中resetMemberMap的authCode
		if(resetPwDTO.getAuthCode().equals(authCode)) {
			resetPwDTO.setSuccessful(true);
//			System.out.println("驗證成功");
			writePojo2Json(response, resetPwDTO);
		}
		else {
			resetPwDTO.setSuccessful(false);
//			System.out.println("驗證失敗");
			writePojo2Json(response, resetPwDTO);
		}
	}
}
