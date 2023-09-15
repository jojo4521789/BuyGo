package web.front_end.member.opa.checkout.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.opa.order.entity.OpaOrderdetails;
import web.front_end.member.opa.order.service.OpaOrderService;
import web.front_end.member.opa.order.service.impl.OpaOrderServiceImpl;
import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

@WebServlet("/needLoginApi/opa/orderdetailsCheckout")
public class OrderdetailsCheckoutServlet extends HttpServlet{
	private static final long serialVersionUID = 4504725733033489986L;
	
	public static final OpaOrderService ORDER_SERVICE = new OpaOrderServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		OpaOrderdetails opaOrderdetails = json2Pojo(req, OpaOrderdetails.class);
		System.out.println(opaOrderdetails);
		if(opaOrderdetails == null) {
			opaOrderdetails = new OpaOrderdetails();
			opaOrderdetails.setMessage("無訂單詳情資料");
			opaOrderdetails.setSuccessful(false);
			writePojo2Json(resp, opaOrderdetails);
		}
		opaOrderdetails = ORDER_SERVICE.addOpaOrderdetails(opaOrderdetails);
		writePojo2Json(resp, opaOrderdetails);
	}
}
