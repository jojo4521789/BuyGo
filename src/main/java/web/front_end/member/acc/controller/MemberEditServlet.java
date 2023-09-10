package web.front_end.member.acc.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.acc.entity.Member;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.acc.util.MemberConstants.SERVICE;
import static web.front_end.member.util.SHA256EncoderUtil.SHA256Encode;
import java.io.IOException;

@WebServlet("/front_end/member/acc/MemberEdit")
public class MemberEditServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Member member = json2Pojo(req, Member.class);
		String action = member.getAction();
//		System.out.println(action +"AAAAAAAAAAAA");
		if("show".equals(action)) {
			member = SERVICE.selectById(member.getMemberNo());
			writePojo2Json(resp, member);
		}else if("checkPassword".equals(action)) {
			String oMemberPw = SHA256Encode(member.getMemberPw());
			member = SERVICE.selectById(member.getMemberNo());
			if(oMemberPw.equals(member.getMemberPw())==false) {
				writePojo2Json(resp, false);
			}
			writePojo2Json(resp, true);
		}else if("editMember".equals(action)){
			String memberPw = SHA256Encode(member.getMemberPw());
			member.setMemberPw(memberPw);
			writePojo2Json(resp, SERVICE.edit(member));
		}
		
	}
	
	
}
