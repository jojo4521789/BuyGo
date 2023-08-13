package web.back_end.lpa.order.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.back_end.lpa.order.service.OrderService;
import web.back_end.lpa.order.service.OrderServiceImpl;

/**
 * Servlet implementation class ShowOrdersServlet
 */
@WebServlet("/ShowOrdersServlet")
public class ShowOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService service = new OrderServiceImpl();
	CommonUtil commonUtil = new CommonUtil();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		commonUtil.writePojo2Json(response, service.findAll());
	}

}
