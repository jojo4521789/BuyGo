package web.back_end.news.news;
import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.guest.news.util.NewsConstants.SERVICE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.guest.news.entity.News;
@WebServlet("/back_end/news/news/NewsDelete")
public class NewsDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html:charset=utf-8");
		News news = json2Pojo(req, News.class);
		
		if(SERVICE.remove(news.getNewsNo())) {
			System.out.println("succes");
			writePojo2Json(resp, "yes");

		}else {
			System.out.println("unsucces");
			writePojo2Json(resp, "false");

		}
	}
	
}
