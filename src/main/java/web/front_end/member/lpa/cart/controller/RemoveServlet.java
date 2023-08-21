package web.front_end.member.lpa.cart.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import java.io.IOException;
import static web.front_end.member.lpa.cart.util.LpaCartConstants.SERVICE;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import core.entity.Core;
import web.front_end.member.lpa.cart.entity.LpaCart;

@WebServlet("front_end/lpa/remove")
public class RemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final Integer lpaProdNo = json2Pojo(request, LpaCart.class).getLpaProdNo();
		final Core core = new Core();
		if (lpaProdNo == null) {
			core.setMessage("查無商品ID，無法刪除");
			core.setSuccessful(false);
		} else {
			core.setSuccessful(SERVICE.remove(lpaProdNo));
		}
		writePojo2Json(response, core);
	}
}
