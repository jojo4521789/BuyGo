package web.front_end.member.pa.order.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.pa.order.service.PaSoService;
import web.front_end.member.pa.order.service.impl.PaSoServiceImpl;


@WebServlet("/needLoginApi/getProdPic")
public class getPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaSoService service = new PaSoServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("執行抓圖");
		Integer prodNo = Integer.valueOf(req.getParameter("prodNo"));
		
		resp.setContentType("text/plain");
		try {
			resp.getWriter().write(service.showFirstImage(prodNo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		resp.setContentType("image/png");
//
//		try (OutputStream out = resp.getOutputStream()){
//			out.write(service.showFirstImage(prodNo));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
	}
}
