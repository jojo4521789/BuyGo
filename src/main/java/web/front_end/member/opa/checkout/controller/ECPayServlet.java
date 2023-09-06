package web.front_end.member.opa.checkout.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ecpay.payment.integration.domain.AioCheckOutALL;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.opa.checkout.util.ECPayCheckoutConstants.SERVICE;

@WebServlet("/opa/checkout/ecpay")
public class ECPayServlet extends HttpServlet{
	private static final long serialVersionUID = 5779735783426257369L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		AioCheckOutALL obj = json2Pojo(req, AioCheckOutALL.class);
		String form = SERVICE.getECpayForm(obj);
		req.getSession().setAttribute("ECPayForm", form);
		String url = "/front_end/pages/member/opa/checkout/ECPayForm.jsp";
//		RequestDispatcher successView = req.getRequestDispatcher(url);
//		successView.forward(req, resp);
//		resp.sendRedirect(req.getContextPath() + url);
		writePojo2Json(resp, req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + url);
	}
	
}
