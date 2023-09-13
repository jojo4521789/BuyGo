package web.front_end.member.forum.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.forum.util.RpArticleConstants.SERVICE;

import web.front_end.member.forum.entity.RpArticle;

@WebServlet("/api/rpArticle/manage")
public class RpArticleManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		List<RpArticle> rpArticlelList = SERVICE.finAll();
		writePojo2Json(response, rpArticlelList);
	}
}
