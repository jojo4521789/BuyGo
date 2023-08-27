package web.front_end.guest.news.controller;

import java.io.IOException;
import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.guest.news.util.NewsConstants.SERVICE;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.guest.news.entity.News;

@WebServlet("/front_end/guest/news/NewsIncrese")
public class NewsIncrese extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html:charset=utf-8");
		
		News news = json2Pojo(req,News.class);
		news = SERVICE.increse(news);
		writePojo2Json(resp, news);
	}
	
}
 