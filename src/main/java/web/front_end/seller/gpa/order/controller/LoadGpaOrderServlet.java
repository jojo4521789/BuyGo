package web.front_end.seller.gpa.order.controller;

import static core.util.CommonUtil.writePojo2Json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static web.front_end.seller.gpa.order.util.GpaSoConstants.SERVICE;

import web.front_end.seller.gpa.order.entity.GpaSo;

@WebServlet("/front_end/seller/order/loadGpaOrder")
public class LoadGpaOrderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		Integer selectPage = Integer.parseInt(request.getParameter("page"));
		System.out.println("selectPage:" + selectPage);
		List<GpaSo> gpsSolist = SERVICE.loadBySoStat(selectPage);
		
		writePojo2Json(response, gpsSolist);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		
		
	}
	

}
