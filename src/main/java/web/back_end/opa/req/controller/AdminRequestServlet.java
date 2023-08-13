package web.back_end.opa.req.controller;

import static web.back_end.opa.req.util.OpaRequestConstants.SERVICE;
import static core.util.CommonUtil.writePojo2Json;
import core.entity.Core;
import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.opa.req.entity.OpaRequest;



@WebServlet("/admin/opa/request")
public class AdminRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<OpaRequest> orders = SERVICE.findAllRequests();
		writePojo2Json(response, orders);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final Core core = new Core();
		if (request.getParameter("id") == null || request.getParameter("status") == null) {
			core.setSuccessful(false);
			core.setMessage("invalid parameter");
			writePojo2Json(response, core);
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
        int status = Integer.parseInt(request.getParameter("status"));
        boolean success = SERVICE.updateStatus(id, status);
        core.setSuccessful(success);
		writePojo2Json(response, core);
	}
}