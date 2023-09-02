package web.front_end.member.opa.cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.entity.Core;
import web.front_end.member.opa.cart.entity.OpaCart;
import web.front_end.member.opa.cart.entity.OpaCartId;
import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.opa.cart.util.OpaCartConstants.SERVICE;

@WebServlet("/needLoginApi/opa/cart/remove")
public class OpaCartRemoveServlet extends HttpServlet{
	private static final long serialVersionUID = -2651434180259062418L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(); // 取得當前請求的Session
		Integer memberNo = (Integer)(session.getAttribute("memberNo"));
		final OpaCartId opaCartId = json2Pojo(req, OpaCart.class).getOpaCartId();
		opaCartId.setMemberNo(memberNo);
		final Core core = new Core();
		core.setSuccessful(SERVICE.remove(opaCartId));
		writePojo2Json(resp, core);
	}
}
