package web.front_end.member.opa.cart.controller;

import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.opa.cart.util.OpaCartConstants.SERVICE;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.opa.cart.entity.OpaCart;

@WebServlet("/needLoginApi/opa/cart/selectByMember")
public class OpaCartSelectByMemberServlet extends HttpServlet{
	private static final long serialVersionUID = -8675834435426874499L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession(); // 取得當前請求的Session
		Integer memberNo = (Integer)(session.getAttribute("memberNo"));
		List<OpaCart> opaCartList = SERVICE.selectByMemberNo(memberNo);
		writePojo2Json(resp, opaCartList);
	}
}
