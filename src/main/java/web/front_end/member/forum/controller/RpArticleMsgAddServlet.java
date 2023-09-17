package web.front_end.member.forum.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static web.front_end.member.forum.util.RpArticleMsgConstants.SERVICE;
import web.front_end.member.forum.entity.RpArticleMsg;

@WebServlet("/api/rparticleMsg/add")
public class RpArticleMsgAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RpArticleMsg rpArticleMsg = json2Pojo(request, RpArticleMsg.class);
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		Integer memberNo = (Integer) (session.getAttribute("memberNo"));
		
	
		if (rpArticleMsg == null) {
			rpArticleMsg = new RpArticleMsg();
			rpArticleMsg.setMessage("無論壇文章留言");
			rpArticleMsg.setSuccessful(false);
			writePojo2Json(response, rpArticleMsg);
		} else {
			rpArticleMsg = SERVICE.add(memberNo, rpArticleMsg);
			writePojo2Json(response, rpArticleMsg);
		}
	}
}
