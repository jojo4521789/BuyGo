package web.front_end.member.pa.cart.controller;

import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.pa.cart.until.PaCartConstants.SERVICE;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.pa.cart.entity.PaCart;

//用會員編號去抓取商品資訊
public class PaCartSelectBySellerServlet extends HttpServlet{

	private static final long serialVersionUID = 1967982237081826259L;
	 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();
		Integer memberNo = (Integer)(session.getAttribute("memberNo"));
		List<PaCart> paCartList = SERVICE.selectBySellerNo(memberNo);
		writePojo2Json(resp, paCartList);
	}
	
	

}
