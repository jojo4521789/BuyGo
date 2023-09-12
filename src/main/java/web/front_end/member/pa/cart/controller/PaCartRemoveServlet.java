package web.front_end.member.pa.cart.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.pa.cart.until.PaCartConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.entity.Core;
import web.front_end.member.pa.cart.entity.PaCart;
import web.front_end.member.pa.cart.entity.PaCartId;

@WebServlet("/needLoginApi/pa/cart/removeProd")
public class PaCartRemoveServlet extends HttpServlet{

	private static final long serialVersionUID = -5529342186727807839L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		Integer memberNo = (Integer) session.getAttribute("memberNo");
		final PaCartId paCartId = json2Pojo(req, PaCart.class).getPaCartId();
		paCartId.setMemberNo(memberNo);
		final Core core = new Core();
		core.setSuccessful(SERVICE.remove(paCartId));
		writePojo2Json(resp, core);
	}
	
}
