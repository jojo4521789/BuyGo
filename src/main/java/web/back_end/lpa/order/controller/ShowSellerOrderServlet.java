package web.back_end.lpa.order.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.back_end.lpa.order.service.LpaSoService;
import web.back_end.lpa.order.service.impl.LpaSoServiceImpl;

@WebServlet("/needLoginApi/loadLpaSellerOrder")
public class ShowSellerOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LpaSoService service = new LpaSoServiceImpl();
	CommonUtil commonUtil = new CommonUtil();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().setAttribute("memberNo", 1);
		Integer sellerNo = (Integer) req.getSession().getAttribute("memberNo"); // 取得登入會員(賣家)資訊
		Byte status = Byte.parseByte(req.getParameter("page"));
		System.out.println(status);
		commonUtil.writePojo2Json(resp, service.findSellerOrders(sellerNo, status));
	}
}
