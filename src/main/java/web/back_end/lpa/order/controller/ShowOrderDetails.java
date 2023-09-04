package web.back_end.lpa.order.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.back_end.lpa.order.service.LpaSoService;
import web.back_end.lpa.order.service.impl.LpaSoServiceImpl;

@WebServlet("/needLoginApi/showOrderDetails")
public class ShowOrderDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LpaSoService service = new LpaSoServiceImpl();
	CommonUtil commonUtil = new CommonUtil();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		String lpaSoSeq = req.getParameter("lpaSoSeq");
		commonUtil.writePojo2Json(resp, service.findOrderDetailsByLpaSoSeq(lpaSoSeq));

	}
}
