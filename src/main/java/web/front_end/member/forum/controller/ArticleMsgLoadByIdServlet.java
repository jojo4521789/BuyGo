package web.front_end.member.forum.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.forum.util.ArticleMsgConstants.SERVICE;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.forum.entity.ArticleMsg;

@WebServlet("/api/articleMsg/loadarticlelList")
public class ArticleMsgLoadByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // 取得當前請求的Session
		response.setCharacterEncoding("UTF-8");
		
		ArticleMsg articleMsg = json2Pojo(request, ArticleMsg.class);
		Integer articleNo = articleMsg.getArticleNo();
		List<ArticleMsg> articleMsgList = SERVICE.loadArticleMsgByArticleNo(articleNo);

		if(articleMsgList.size() !=0) {
			writePojo2Json(response, articleMsgList);
		}
	}
	
	
}
