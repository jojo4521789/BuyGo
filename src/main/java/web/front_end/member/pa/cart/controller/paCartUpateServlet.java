package web.front_end.member.pa.cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.pa.cart.until.PaCartConstants.SERVICE;

import web.front_end.member.pa.cart.entity.PaCart;

@WebServlet("/needLoginApi/pa/cart/update")
public class paCartUpateServlet extends HttpServlet{
	
	private static final long serialVersionUID = -4496357754347071808L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();
		Integer memberNo = (Integer)(session.getAttribute("memberNo"));
		PaCart paCart = json2Pojo(req, PaCart.class);
		paCart.getPaCartId().setMemberNo(memberNo);
		writePojo2Json(resp, SERVICE.update(paCart));
		
	}

}
