package web.front_end.member.forum.controller;

import static core.util.CommonUtil.writePojo2Json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static web.front_end.member.forum.util.ForumArticleConstants.SERVICE;

import web.front_end.member.forum.entity.ForumArticle;

@WebServlet("/api/forumArticle/manage")
public class ManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		List<ForumArticle> forumArticleslList = SERVICE.finAll();
		writePojo2Json(response, forumArticleslList);
	}
}
