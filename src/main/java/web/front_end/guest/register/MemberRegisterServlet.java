package web.front_end.guest.register;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.acc.util.MemberConstants.SERVICE;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.front_end.member.util.SHA256EncoderUtil;
import web.front_end.member.acc.entity.Member;
import static web.front_end.member.util.SHA256EncoderUtil.SHA256Encode;;

@WebServlet("/api/front_end/guest/register/MemberRegister")
public class MemberRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		  resp.setContentType("application/json;charset=utf-8");
//		  req.setCharacterEncoding("utf-8");
//		System.out.println("11111111111111");
//		req.setCharacterEncoding("UTF-8");
//		int contentLength = req.getContentLength();
//		byte[] bytes = new byte[contentLength];
//		for (int i = 0; i < contentLength;) {
//			int readLength = req.getInputStream().read(bytes, i, contentLength - 1);
//			if (readLength == -1) {
//				break;
//			}
//			i += readLength;
//		}
//		String s = new String(bytes);

//		System.out.println(s);

		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html:charset=utf-8");

		Member member = json2Pojo(req, Member.class);
		String action = member.getAction();
		if ("register".equals(action)) {
			String memberPassWord = member.getMemberPw();
			member.setMemberPw(SHA256Encode(memberPassWord));
			
			member = SERVICE.register(member);
			if(member.getMessage().equals("新增錯誤")) {
				writePojo2Json(resp, false);
			}
			writePojo2Json(resp, true);
		} else if ("testmemberAcct".equals(action)) {
			List<Member> listMember = SERVICE.findall();
			String omemberAcct = member.getMemberAcct();
			Boolean isAcount = false;
//			System.out.println(omemberAcct);
			for (Member member2 : listMember) {
				if (omemberAcct.equals(member2.getMemberAcct())) {
					isAcount = true;
					break;
				}
			}
			writePojo2Json(resp, isAcount);
		} else if ("testmemberPhone".equals(action)) {
			List<Member> listMember = SERVICE.findall();
			String omemberPhone = member.getMemberPhone();
			Boolean isAcount = false;
//			System.out.println(omemberPhone);
			for (Member member2 : listMember) {
				if (omemberPhone.equals(member2.getMemberPhone())) {
					isAcount = true;
					break;
				}
			}
			writePojo2Json(resp, isAcount);
		} else if ("testmemberEmail".equals(action)) {
			List<Member> listMember = SERVICE.findall();
			String omemberEmail = member.getMemberEmail();
			Boolean isAcount = false;
//		System.out.println(omemberEmail);
			for (Member member2 : listMember) {
				if (omemberEmail.equals(member2.getMemberEmail())) {
					isAcount = true;
					break;
				}
			}
			writePojo2Json(resp, isAcount);
		} else if ("testmemberId".equals(action)) {
			List<Member> listMember = SERVICE.findall();
			String omemberId = member.getMemberId();
			Boolean isAcount = false;
//	System.out.println(omemberPhone);
			for (Member member2 : listMember) {
				if (omemberId.equals(member2.getMemberId())) {
					isAcount = true;
					break;
				}
			}
			writePojo2Json(resp, isAcount);
		}

	}

}
