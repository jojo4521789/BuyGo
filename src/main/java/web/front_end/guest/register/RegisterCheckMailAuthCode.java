package web.front_end.guest.register;

import java.io.IOException;
import java.lang.reflect.Member;
import java.util.Map;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/api/front_end/guest/register/RegisterCheckMailAuthCode")
public class RegisterCheckMailAuthCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
//		String checkCode = req.getParameter("checkCode");
		String authCode = (String) ((Map) session.getAttribute("checkMailMap")).get("authCode");
		int contentcode = req.getContentLength();
		byte[] bytes = new byte[contentcode];
		for(int i = 0;i<contentcode;) {
			int readlength = req.getInputStream().read(bytes,i,contentcode -1);
			if(readlength == -1) {
				break;
			}
				i += readlength;
		}
		String s = new String(bytes);
		String authCodes = "\""+authCode+"\"";
		System.out.println(s);
		System.out.println(authCodes);
		if (s.equals(authCodes)) {
			writePojo2Json(resp, true);
		} else {
			writePojo2Json(resp, false);
		}
	}

}
