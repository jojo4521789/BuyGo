package web.back_end.opa.prod.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.opa.prod.util.PrpicsConstants.SERVICE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.entity.Core;
import web.back_end.opa.prod.entity.Prpics;

@WebServlet("/api/opa/prpics/remove")
public class PrpicsRemoveServlet extends HttpServlet{
	private static final long serialVersionUID = -7408100172491320609L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final Integer opaPrpicsNo = json2Pojo(req, Prpics.class).getOpaPrpicsNo();
		final Core core = new Core();
		if(opaPrpicsNo == null) {
			core.setMessage("查無商品圖片ID，無法刪除");
			core.setSuccessful(false);
		}else {
			core.setSuccessful(SERVICE.remove(opaPrpicsNo));
		}
		writePojo2Json(resp, core);
	}
}
