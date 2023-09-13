package web.front_end.member.forum.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import web.front_end.member.forum.entity.ArticleCollList;
import web.front_end.member.forum.entity.ArticleMsg;
import web.front_end.member.forum.entity.ForumArticle;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.forum.util.ArticleCollListConstants.SERVICE;

@WebServlet("/api/articleCollLis/add")
public class ArticleCollListByAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		ArticleCollList articleCollList_el = json2Pojo(request, ArticleCollList.class);
		Integer articleNo = articleCollList_el.getArticleNo();
		
		HttpSession session = request.getSession();
		Integer memberNo = (Integer) (session.getAttribute("memberNo"));

		// 測試用"登入功能加進來"(記得刪)
		if (memberNo == null) {
			memberNo = 3;
		}

		boolean articleCollList = SERVICE.select(memberNo, articleNo);
		
		if (!articleCollList) {
			System.out.println("文章收藏成功");
			articleCollList_el = SERVICE.add(memberNo, articleCollList_el);
			writePojo2Json(response, articleCollList_el);
		} else {
			System.out.println("此會員已收藏過文章");
		}
	}

}
