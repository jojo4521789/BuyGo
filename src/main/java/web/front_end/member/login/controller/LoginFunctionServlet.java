package web.front_end.member.login.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static web.front_end.member.login.util.LoginConstants.SERVICE;
import web.front_end.member.acc.entity.Member;
import static web.front_end.member.util.SHA256EncoderUtil.SHA256Encode;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/front_end/loginFunction")
public class LoginFunctionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		Member member = json2Pojo(request, Member.class);

		boolean b = SERVICE.CheckMemberAcctAndPassword(member.getMemberAcct(), SHA256Encode(member.getMemberPw())); // 密碼需經過SHA256轉換
		member.setSuccessful(b); // 將成功或失敗的布林值設定在member的successful內
		if(b) { // 登入成功
			// 向前端設置Cookie
			HttpSession session = request.getSession(); // 取得當前請求的Session
			String sessionId = session.getId();// 取得Servlet Container指定的sessionID，同時將該sessionID設置於前端的cookie內
//			System.out.println("登入成功, session.getId:" + sessionId);
			Integer memberNo= SERVICE.LoadMemberBymemberAcctAndmemberPw(member.getMemberAcct(), SHA256Encode(member.getMemberPw())).getMemberNo(); // 取得完成登入者的memberNo
			
			session.setAttribute("sessionId", sessionId); // 將客戶的SessionId放入sessionAttribute，供後續判斷是否為登入狀態
			session.setAttribute("memberNo", memberNo); // 將客戶的memberNo放入sessionAttribute，供後續換頁時由後端提供給前端
			
			writePojo2Json(response, member);
		}else { // 登入失敗
//			System.out.println("登入失敗");
			writePojo2Json(response, member);
		}
	}
	
}
