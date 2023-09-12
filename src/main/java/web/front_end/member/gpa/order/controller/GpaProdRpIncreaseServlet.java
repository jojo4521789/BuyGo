package web.front_end.member.gpa.order.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.gpa.order.util.GpaProdRpConstants.SERVICE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.gpa.order.entity.GpaProdRp;
@WebServlet("/front_end/member/gpa/order/GpaProdRpIncrease")
public class GpaProdRpIncreaseServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html:charset=utf-8");
		
		GpaProdRp gpaProdRp = json2Pojo(req, GpaProdRp.class);
//		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA");
		
		writePojo2Json(resp, SERVICE.increse(gpaProdRp));
	}
	
}
