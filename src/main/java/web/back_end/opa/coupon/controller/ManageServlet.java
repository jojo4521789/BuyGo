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
import static web.back_end.opa.coupon.util.CouponConstants.SERVICE;
import static core.util.CommonUtil.writePojo2Json;

@WebServlet("/opa/coupon/manage")
public class ManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		List<Coupon> couponList = SERVICE.findAll();
		writePojo2Json(response, couponList);
	}

}
