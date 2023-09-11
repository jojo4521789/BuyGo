package web.front_end.member.opa.cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.opa.cart.entity.OpaCart;
import web.front_end.member.opa.cart.entity.OpaCartId;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.opa.cart.util.OpaCartConstants.SERVICE;

@WebServlet("/needLoginApi/opa/cart/selectById")
public class OpaCartSelectByIdServlet extends HttpServlet{
	private static final long serialVersionUID = 5071462630958698943L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession(); // 取得當前請求的Session
		Integer memberNo = (Integer)(session.getAttribute("memberNo"));
		OpaCartId opaCartId = json2Pojo(req, OpaCart.class).getOpaCartId();
		opaCartId.setMemberNo(memberNo);
		OpaCart opaCart = SERVICE.selectById(opaCartId);
		writePojo2Json(resp, opaCart);
	}
}
