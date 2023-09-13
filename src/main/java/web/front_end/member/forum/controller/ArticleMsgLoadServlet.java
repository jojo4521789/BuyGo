package web.front_end.member.forum.controller;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.forum.entity.ArticleMsg;

import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.forum.util.ArticleMsgConstants.SERVICE;

@WebServlet("/api/articleMsg/loadarticleMsglList")
public class ArticleMsgLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // 取得當前請求的Session
		response.setCharacterEncoding("UTF-8");
		
		List<ArticleMsg> articleMsglList = SERVICE.loadArticleMsgByArticleNo((Integer)(session.getAttribute("articleNo")));
		if(articleMsglList.size() !=0) {
			writePojo2Json(response, articleMsglList);
		}
	}
}