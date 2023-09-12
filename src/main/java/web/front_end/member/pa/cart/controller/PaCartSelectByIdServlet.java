package web.front_end.member.pa.cart.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.pa.cart.until.PaCartConstants.SERVICE;

import web.front_end.member.pa.cart.entity.PaCart;
import web.front_end.member.pa.cart.entity.PaCartId;

@WebServlet("/needLoginApi/pa/cart/selectById")
public class PaCartSelectByIdServlet extends HttpServlet{

	private static final long serialVersionUID = 4688843839857021954L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();
		Integer memberNo = (Integer)(session.getAttribute("memberNo"));
		PaCartId paCartId = json2Pojo(req, PaCart.class).getPaCartId();
		paCartId.setMemberNo(memberNo);
		PaCart paCart = SERVICE.selectById(paCartId);
		writePojo2Json(resp, paCart);
	}
}
