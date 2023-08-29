package web.front_end.member.lpa.cart.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.lpa.cart.util.LpaCartConstants.SERVICE;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.front_end.member.lpa.cart.entity.LpaCart;

//購物車新增商品
@WebServlet("/front_end/lpa/add_to_cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LpaCart lpaCart = json2Pojo(request, LpaCart.class);
		
		if (lpaCart == null) {
			lpaCart = new LpaCart();
			lpaCart.setMessage("無商品資訊");
			lpaCart.setSuccessful(false);
			writePojo2Json(response, lpaCart);
			
		}
		lpaCart = SERVICE.add(lpaCart);
		writePojo2Json(response, lpaCart);
	}
}