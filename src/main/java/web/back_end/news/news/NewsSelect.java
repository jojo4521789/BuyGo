package web.back_end.news.news;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.guest.news.entity.News;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.guest.news.util.NewsConstants.SERVICE;

import java.io.IOException;
import java.util.List;

@WebServlet("/back_end/news/news/NewsSelect")
public class NewsSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html:charset=utf-8");
		News news = json2Pojo(req, News.class);
		List<News> newss = SERVICE.selectByinput(news.getNewsTitle());
		if(newss.size() != 0) {
			writePojo2Json(resp, newss);
		}
	}

}
