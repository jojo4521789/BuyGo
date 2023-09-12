package web.front_end.member.pa.order.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.front_end.member.pa.order.service.PaSoService;
import web.front_end.member.pa.order.service.impl.PaSoServiceImpl;

@WebServlet("/needLoginApi/loadPaSellerOrder")
public class ShowSellerOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaSoService service = new PaSoServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Integer sellerNo = (Integer) req.getSession().getAttribute("memberNo"); // 取得登入會員(賣家)資訊
		Byte status = Byte.parseByte(req.getParameter("page"));
		CommonUtil.writePojo2Json(resp, service.findSellerOrders(sellerNo, status));
	}
}
