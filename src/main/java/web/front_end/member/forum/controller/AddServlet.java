package web.front_end.member.forum.controller;

import static core.util.CommonUtil.json2Pojo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import static core.util.CommonUtil.writePojo2Json;
import web.front_end.member.forum.entity.ForumArticle;
import static web.front_end.member.forum.util.ForumArticleConstants.SERVICE;

@WebServlet("/api/forumArticle/add")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ForumArticle forumArticle = json2Pojo(request, ForumArticle.class);
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		Integer memberNo = (Integer)(session.getAttribute("memberNo"));
		//System.out.print("memberNo : " + memberNo);
		
		//測試用"登入功能加進來"(記得刪)
		if(memberNo == null) {
			memberNo = 1;
		}
		
		if (forumArticle == null) {
			forumArticle = new ForumArticle();
			forumArticle.setMessage("無論壇文章");
			forumArticle.setSuccessful(false);
			writePojo2Json(response, forumArticle);
		}
		
		forumArticle = SERVICE.add( memberNo , forumArticle);
		writePojo2Json(response, forumArticle);
	}

}
