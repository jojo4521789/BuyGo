package web.front_end.member.forum.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.entity.Core;
import web.front_end.member.forum.entity.ArticleCollList;
import web.front_end.member.forum.entity.ForumArticle;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.forum.util.ArticleCollListConstants.SERVICE;

@WebServlet("/api/articleCollList/remove")
public class ArticleCollListRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		final Integer articleNo = json2Pojo(request, ArticleCollList.class).getArticleNo();
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		Integer memberNo = (Integer) (session.getAttribute("memberNo"));
		// System.out.print("memberNo : " + memberNo);

		// 測試用"登入功能加進來"(記得刪)
//		if (memberNo == null) {
//			memberNo = 2;
//		}
		
		System.out.println("articleNo" + articleNo);
		System.out.println("memberNo" + memberNo);
		
		final Core core = new Core();
		if (memberNo == null) {
			core.setMessage("查無論壇文章ID，無法刪除");
			core.setSuccessful(false);

		} else {
			core.setSuccessful(SERVICE.remove(memberNo, articleNo));
		}
		writePojo2Json(response, core);
	}

}
