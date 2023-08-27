package web.front_end.guest.register;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.acc.util.MemberConstants.SERVICE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.acc.entity.Member;

@WebServlet("/front_end/guest/register/MemberRegister")
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

//
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html:charset=utf-8");
//		String memberGender = req.getParameter("memberGender");
//		System.out.println(memberGender);
		Member member = json2Pojo(req,Member.class);
//		System.out.println(member);
		
		if (member == null) {
			member = new Member();
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
			writePojo2Json(resp, member);
			return;
		}
		
		member = SERVICE.register(member);
		writePojo2Json(resp, member);

	}

}
