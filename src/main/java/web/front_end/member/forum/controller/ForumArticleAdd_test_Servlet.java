package web.front_end.member.forum.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.forum.util.ForumArticleConstants.SERVICE;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import web.front_end.member.forum.entity.ForumArticle;

@WebServlet("/api/forumArticle/loadarticlelList_test")
public class ForumArticleAdd_test_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Integer memberNo = (Integer) (session.getAttribute("memberNo"));

		// 測試用"登入功能加進來"(記得刪)
		if (memberNo == null) {
			memberNo = 2;
		}
		
//		ForumArticle articleMsg = json2Pojo(request, ForumArticle.class);
//		Integer articleNo = articleMsg.getArticleNo();
		List<ForumArticle> articleMsgList = SERVICE.loadForumArticleBymemberNo_test(memberNo);

		if(articleMsgList.size() !=0) {
			writePojo2Json(response, articleMsgList);
		}
	}
	
	
}
