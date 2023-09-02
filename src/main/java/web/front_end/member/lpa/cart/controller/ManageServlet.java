package web.front_end.member.lpa.cart.controller;

import static core.util.CommonUtil.writePojo2Json;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static web.front_end.member.lpa.cart.util.LpaCartConstants.SERVICE;
import web.front_end.member.lpa.cart.entity.LpaCart;

@WebServlet("/api/front_end/lpa/manage")
public class ManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		response.setContentType("text/html;charset=utf-8");
//		List<LpaCart> lpaCartList = SERVICE.finall();
//		writePojo2Json(response, lpaCartList);
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		List<LpaCart> lpaCartList = SERVICE.finall();
		writePojo2Json(response, lpaCartList);
	}

}
