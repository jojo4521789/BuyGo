package web.back_end.member.wallet.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.member.wallet.util.WalletMemberConstants.SERVICE;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.acc.entity.Member;

@WebServlet("/api/back_end/loadMemberList") // 後台會員登入加入後記得改
public class LoadMemberListServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession(); // 取得當前請求的Session
		response.setCharacterEncoding("UTF-8");
		Member member = json2Pojo(request, Member.class);
		
		List<Member> memberList = SERVICE.loadMemberList(member);
		
		writePojo2Json(response, memberList);
	}
}
