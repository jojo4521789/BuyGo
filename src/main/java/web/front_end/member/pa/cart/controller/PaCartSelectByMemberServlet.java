package web.front_end.member.pa.cart.controller;

import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.pa.cart.until.PaCartConstants.SERVICE;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.pa.cart.entity.PaCart;

@WebServlet("/needLoginApi/pa/cart/selectByMember")
public class PaCartSelectByMemberServlet extends HttpServlet{
	
	private static final long serialVersionUID = -2443283968980324580L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();
		Integer memberNo = (Integer)(session.getAttribute("memberNo"));
		List<PaCart> paCartList = SERVICE.selectByMemberNo(memberNo);
		writePojo2Json(resp, paCartList);
	}

}
