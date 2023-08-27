package web.back_end.opa.coupon.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.opa.coupon.entity.Coupon;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.opa.coupon.util.CouponConstants.SERVICE;

@WebServlet("/opa/coupon/add")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Coupon coupon = json2Pojo(request, Coupon.class);
		
		if(coupon == null) {
			coupon = new Coupon();
			coupon.setMessage("無優惠券資訊");
			coupon.setSuccessful(false);
			writePojo2Json(response, coupon);
		}
		
		coupon = SERVICE.add(coupon);
		writePojo2Json(response, coupon);
	}

}
