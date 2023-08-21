package web.front_end.member.lpa.cart.controller;

import static core.util.CommonUtil.json2Pojo;
import java.io.IOException;
import static web.front_end.member.lpa.cart.util.LpaCartConstants.SERVICE;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static core.util.CommonUtil.writePojo2Json;

import web.front_end.member.lpa.cart.entity.LpaCart;

public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		final HttpSession session = request.getSession();
		LpaCart lpaCart = json2Pojo(request, LpaCart.class);
		
		writePojo2Json(response, SERVICE.update(lpaCart));
	
		
	}

}