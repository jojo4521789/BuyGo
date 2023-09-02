package web.front_end.member.login.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.login.util.LoginConstants.SERVICE;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.acc.entity.Member;
import web.front_end.member.login.dto.LoginCheckDTO;

@WebServlet("/api/front_end/checkMemberEmail")
public class CheckMemberEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		Member member = json2Pojo(request, Member.class);

		if (SERVICE.LoadMemberByMemberAcctAndMemberEmail(member.getMemberAcct(), member.getMemberEmail()) != null) {
			member = SERVICE.LoadMemberByMemberAcctAndMemberEmail(member.getMemberAcct(), member.getMemberEmail());
			member.setSuccessful(true); // 於member的successful設定true，供前端判斷後端的查詢結果
			writePojo2Json(response, member);
		}
		else {
			member.setSuccessful(false);
			writePojo2Json(response, member);
		}
	}
}
