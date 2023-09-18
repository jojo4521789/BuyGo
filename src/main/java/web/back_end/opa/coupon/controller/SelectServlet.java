package web.back_end.opa.coupon.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.opa.coupon.entity.Coupon;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.opa.coupon.util.CouponConstants.SERVICE;

@WebServlet("/api/opa/coupon/search")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		Coupon coupon = json2Pojo(request, Coupon.class);
		String input = coupon.getOpaCouponName();
		List<Coupon> couponList = SERVICE.findPart(input);
		writePojo2Json(response, couponList);
	}
}
