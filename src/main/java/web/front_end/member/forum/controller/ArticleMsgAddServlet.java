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
import static web.front_end.member.forum.util.ArticleMsgConstants.SERVICE;

import web.front_end.member.forum.entity.ArticleMsg;

@WebServlet("/needLoginApi/articleMsg/add")
public class ArticleMsgAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticleMsg articleMsg = json2Pojo(request, ArticleMsg.class);
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		Integer memberNo = (Integer) (session.getAttribute("memberNo"));
		
		
		// 測試用的
		System.out.println("articleMsg：" + articleMsg);
		System.out.println("memberNo：" + memberNo);
		
		if (articleMsg == null || memberNo == null) {
			articleMsg = new ArticleMsg();
			articleMsg.setMessage("無論壇文章留言");
			articleMsg.setSuccessful(false);
			writePojo2Json(response, articleMsg);
		} else {
			articleMsg = SERVICE.add(memberNo, articleMsg);
			writePojo2Json(response, articleMsg);
		}
	}
}
