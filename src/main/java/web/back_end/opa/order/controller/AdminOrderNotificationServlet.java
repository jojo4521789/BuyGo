package web.back_end.opa.order.controller;

import static core.util.CommonUtil.writePojo2Json;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.opa.order.entity.OpaOrder;
import web.front_end.member.notification.entity.Notification;
import core.entity.Core;



@WebServlet("/needLoginApi/admin/opa/order/notify")
public class AdminOrderNotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final Core core = new Core();
		response.setCharacterEncoding("UTF-8");
		if (request.getParameter("id") == null) {
			core.setSuccessful(false);
			core.setMessage("invalid parameter");
			writePojo2Json(response, core);
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Notification notification = web.front_end.member.opa.order.util.OpaOrderConstants.SERVICE.getCancelNotification(id);
			Integer inserted_id = web.front_end.member.notification.util.NotificationConstants.SERVICE.insert(notification);
			boolean success = inserted_id > 0;
			core.setSuccessful(success);
			writePojo2Json(response, core);
		} catch (Exception e) {
			core.setSuccessful(false);
            core.setMessage(e.getMessage());
            writePojo2Json(response, core);
		}
	}
}
