package web.back_end.opa.order.controller;

import static web.front_end.member.opa.order.util.OpaOrderConstants.SERVICE;
import static core.util.CommonUtil.writePojo2Json;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.opa.order.entity.OpaOrder;
import core.entity.Core;



@WebServlet("/needLoginApi/admin/opa/order")
public class AdminOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		List<OpaOrder> orders = SERVICE.findAll();
		for(OpaOrder order : orders) {
			order.setOpaOrderdetailses(null);
		}
		response.setCharacterEncoding("UTF-8");
		writePojo2Json(response, orders);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final Core core = new Core();
		response.setCharacterEncoding("UTF-8");
		if (request.getParameter("id") == null || request.getParameter("status") == null) {
			core.setSuccessful(false);
			core.setMessage("invalid parameter");
			writePojo2Json(response, core);
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
        int status = Integer.parseInt(request.getParameter("status"));
        Integer failed = Integer.parseInt(request.getParameter("failed"));
		if(failed == -1) 
			failed = null;
        boolean success = SERVICE.updateStatus(id, status, failed);
        core.setSuccessful(success);
		writePojo2Json(response, core);
	}
}
