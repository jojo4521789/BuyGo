package web.back_end.news.news;

import java.io.IOException;
import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.guest.news.util.NewsConstants.SERVICE;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;
import web.front_end.guest.news.entity.News;

@WebServlet("/back_end/news/news/NewsIncrese")
public class NewsIncrese extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 private static final String SERIAL_NUMBER_KEY = "serial_number";
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
		System.out.println(news);
//		Jedis jedis = new Jedis("localhost",6379);
//		String serialNum = "NEWS" + jedis.incr(SERIAL_NUMBER_KEY);
//		jedis.set(serialNum,news.getNewsContent());
//		jedis.close();
	}
	
}
 