package web.front_end.member.pa.order.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.front_end.member.pa.order.service.PaSoService;
import web.front_end.member.pa.order.service.impl.PaSoServiceImpl;

@WebServlet("/needLoginApi/loadPaBuyerOrder")
public class ShowBuyerOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaSoService service = new PaSoServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Integer buyerNo = (Integer)req.getSession().getAttribute("memberNo"); // 取得登入會員資訊
		Byte status = Byte.parseByte(req.getParameter("page"));
//		if (status == 0) {
//			commonUtil.writePojo2Json(resp, service.findAll(memberNo));
//		} else {
			CommonUtil.writePojo2Json(resp, service.findBuyerOrders(buyerNo, status));
//		}
		
	}

}
