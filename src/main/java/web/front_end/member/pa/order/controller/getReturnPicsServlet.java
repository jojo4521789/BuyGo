package web.front_end.member.pa.order.controller;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import redis.clients.jedis.Jedis;

@WebServlet("/needLoginApi/getReturnPics")
public class getReturnPicsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Integer paRtnNo = Integer.parseInt(req.getParameter("paRtnNo"));
		try (Jedis jedis = new Jedis("localhost", 6379)) {
			List<String> rtnPics = jedis.lrange("paRtn" + paRtnNo, 0, jedis.llen("paRtn" + paRtnNo) - 1);
			CommonUtil.writePojo2Json(resp, rtnPics);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
