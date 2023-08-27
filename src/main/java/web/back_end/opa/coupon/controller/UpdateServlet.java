package web.back_end.opa.coupon.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.back_end.opa.coupon.entity.Coupon;
import static web.back_end.opa.coupon.util.CouponConstants.SERVICE;

@WebServlet("/opa/coupon/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		Coupon coupon = json2Pojo(request, Coupon.class);
		
		writePojo2Json(response, SERVICE.update(coupon));
	}

}
