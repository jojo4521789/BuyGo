package web.back_end.opa.coupon.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.entity.Core;
import web.back_end.opa.coupon.entity.Coupon;
import static web.back_end.opa.coupon.util.CouponConstants.SERVICE;

@WebServlet("/api/opa/coupon/remove")
public class RemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Integer opaCouponNo = json2Pojo(request, Coupon.class).getOpaCouponNo();
		final Core core = new Core();
		if(opaCouponNo == null) {
			core.setMessage("查無優惠券ID，無法刪除");
			core.setSuccessful(false);
		}else {
			core.setSuccessful(SERVICE.remove(opaCouponNo));
		}
		writePojo2Json(response, core);
	}

}
