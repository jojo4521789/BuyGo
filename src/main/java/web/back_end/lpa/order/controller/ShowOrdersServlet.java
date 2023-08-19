package web.back_end.lpa.order.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.back_end.lpa.order.entity.Lpa_SO;
import web.back_end.lpa.order.service.OrderService;
import web.back_end.lpa.order.service.OrderServiceImpl;
import web.back_end.lpa.product.entity.Lpa_Prod;

@WebServlet("/loadLpaOrder")
public class ShowOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService service = new OrderServiceImpl();
	CommonUtil commonUtil = new CommonUtil();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		Byte status = Byte.parseByte(request.getParameter("page"));
//		Lpa_SO input = commonUtil.json2Pojo(request, Lpa_SO.class);
		if (status == 0) {
			commonUtil.writePojo2Json(response, service.findAll());
		} else {
			commonUtil.writePojo2Json(response, service.findByOrderStatus(status));
		}
		
	}

}
