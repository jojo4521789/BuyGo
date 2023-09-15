package web.front_end.member.opa.checkout.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.opa.order.entity.OpaOrder;
import web.front_end.member.opa.order.service.OpaOrderService;
import web.front_end.member.opa.order.service.impl.OpaOrderServiceImpl;

@WebServlet("/needLoginApi/opa/orderCheckout")
public class OrderCheckoutServlet extends HttpServlet{
	private static final long serialVersionUID = 8625075111420586219L;
	
	public static final OpaOrderService ORDER_SERVICE = new OpaOrderServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession(); // 取得當前請求的Session
		Integer memberNo = (Integer)(session.getAttribute("memberNo"));
		
		OpaOrder opaOrder = json2Pojo(req, OpaOrder.class);
		opaOrder.setMemberNo(memberNo);
		
		ORDER_SERVICE.save(opaOrder);
		writePojo2Json(resp, opaOrder);
	}
}
