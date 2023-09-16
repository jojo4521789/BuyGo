package web.front_end.member.opa.checkout.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.opa.coupon.entity.CpOwner;
import web.back_end.opa.coupon.entity.CpOwnerId;
import web.back_end.opa.coupon.service.CpOwnerService;
import web.back_end.opa.coupon.service.impl.CpOwnerServiceImpl;
import web.back_end.opa.prod.entity.Prod;
import web.back_end.opa.prod.service.ProdService;
import web.back_end.opa.prod.service.impl.ProdServiceImpl;
import web.front_end.member.opa.cart.entity.OpaCartId;
import web.front_end.member.opa.cart.service.OpaCartService;
import web.front_end.member.opa.cart.service.impl.OpaCartServiceImpl;
import web.front_end.member.opa.order.entity.OpaOrder;
import web.front_end.member.opa.order.entity.OpaOrderdetails;
import web.front_end.member.opa.order.service.OpaOrderService;
import web.front_end.member.opa.order.service.impl.OpaOrderServiceImpl;

@WebServlet("/api/opa/ECPayUpdateOrder")
public class ECPayUpdateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = -6398760183453084146L;

	public static final OpaOrderService ORDER_SERVICE = new OpaOrderServiceImpl();
	public static final CpOwnerService CPOWNER_SERVICE = new CpOwnerServiceImpl();
	public static final OpaCartService OPACART_SERVICE = new OpaCartServiceImpl();
	public static final ProdService PROD_SERVICE = new ProdServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		fixHeaders(resp);
		resp.setContentType("text/html;charset=utf-8");
//		System.out.println("BuyGoOrderId=" + req.getParameter("BuyGoOrderId")); // 取得orderId
//		System.out.println("CouponId=" + req.getParameter("CouponId")); // 取得CouponId
		// 更新訂單為付款狀態
		Integer opaSoNo = Integer.valueOf(req.getParameter("BuyGoOrderId"));
		OpaOrder opaOrder = ORDER_SERVICE.selectById(opaSoNo);
		opaOrder.setOpaSoStatus(Byte.valueOf("1"));
		opaOrder.setOpaRealStatus(Byte.valueOf("1"));
		opaOrder.setOpaRealTotal(opaOrder.getOpaFirAmount());
		ORDER_SERVICE.update(opaOrder);

		// 更新優惠券使用狀態
		Integer memberNo = Integer.valueOf(req.getParameter("memberNo"));
		Integer opaCouponNo = Integer.valueOf(req.getParameter("CouponId"));
		if (opaCouponNo != 0) {
			CpOwnerId cpOwnerId = new CpOwnerId(opaCouponNo, memberNo);
			System.out.println("cpOwnerId=" + cpOwnerId);
			CpOwner cpOwner = CPOWNER_SERVICE.selectByMemberAndCouponNo(cpOwnerId);
			cpOwner.setOpaCpownerStatus(1);
			CPOWNER_SERVICE.update(cpOwner);
		}

		if (opaOrder != null) {
			List<OpaOrderdetails> opaOrderdetailsList = ORDER_SERVICE.selectOrderdetailsByOpaSoNo(opaSoNo);
			for (OpaOrderdetails opaOrderdetails : opaOrderdetailsList) {
				Integer opaProdNo = opaOrderdetails.getId().getOpaProdNo();
				// 刪除購物車品項
				final OpaCartId opaCartId = new OpaCartId(memberNo, opaProdNo);
				Boolean remove = OPACART_SERVICE.remove(opaCartId);
				// 更新商品庫存
				Prod prod = PROD_SERVICE.prodSelectById(opaProdNo);
				prod.setOpaProdStockQty(prod.getOpaProdStockQty() - 1);
				prod.setOpaProdShipQty(prod.getOpaProdShipQty() + 1);
				PROD_SERVICE.update(prod);
			}
		}

	}

	private void fixHeaders(HttpServletResponse resp) {
		// 允許跨域請求的域名，可以是多個域名，用逗號分隔
		resp.setHeader("Access-Control-Allow-Origin", "https://payment-stage.ecpay.com.tw");
		// 允許的HTTP方法
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST");
		// 允許的自定義請求頭
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		// 預檢請求的緩存時間，單位為秒
		resp.setHeader("Access-Control-Max-Age", "3600");
	}

}
