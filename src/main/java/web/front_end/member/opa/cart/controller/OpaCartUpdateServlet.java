package web.front_end.member.opa.cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.opa.cart.entity.OpaCart;
import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.opa.cart.util.OpaCartConstants.SERVICE;

@WebServlet("/needLoginApi/opa/cart/update")
public class OpaCartUpdateServlet extends HttpServlet{
	private static final long serialVersionUID = 6212128875394561761L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession(); // 取得當前請求的Session
		Integer memberNo = (Integer)(session.getAttribute("memberNo"));
		OpaCart opaCart = json2Pojo(req, OpaCart.class);
		opaCart.getOpaCartId().setMemberNo(memberNo);
		writePojo2Json(resp, SERVICE.update(opaCart));
	}
}
