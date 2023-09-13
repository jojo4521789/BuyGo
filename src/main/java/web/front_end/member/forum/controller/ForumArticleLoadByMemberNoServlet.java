package web.front_end.member.forum.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.forum.util.ForumArticleConstants.SERVICE;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.forum.entity.ForumArticle;
@WebServlet("/needLoginApi/forumArticle/loadByMemberNo")
//@WebServlet("/api/forumArticle/loadByMemberNo")
public class ForumArticleLoadByMemberNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		Integer memberNo = (Integer) (session.getAttribute("memberNo"));
		
		//測試用"登入功能加進來"(記得刪)
		if(memberNo == null) {
			memberNo = 2;
		}
		
		List<ForumArticle> forumArticleslList = SERVICE.loadForumArticleBymemberNo(memberNo);
		System.out.println("memberNo：" + memberNo);
		System.out.println("forumArticleslList：" + forumArticleslList.size());
		
		if (forumArticleslList.size() != 0) {
			System.out.println("取得成功");
			writePojo2Json(response, forumArticleslList);
		}
	}

}