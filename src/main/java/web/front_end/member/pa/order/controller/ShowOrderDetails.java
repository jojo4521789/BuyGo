package web.front_end.member.pa.order.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.front_end.member.pa.order.service.PaSoService;
import web.front_end.member.pa.order.service.impl.PaSoServiceImpl;

@WebServlet("/needLoginApi/showOrderDetails")
public class ShowOrderDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaSoService service = new PaSoServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Integer paSoNo = Integer.parseInt(req.getParameter("paSoNo"));
		CommonUtil.writePojo2Json(resp, service.findPaSoByPaSoNo(paSoNo));

	}
}
