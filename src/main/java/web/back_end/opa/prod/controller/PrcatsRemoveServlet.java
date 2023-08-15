package web.back_end.opa.prod.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.entity.Core;
import web.back_end.opa.prod.entity.Prcats;

import static web.back_end.opa.prod.util.PrcatsConstants.SERVICE;
import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

@WebServlet("/prcats/remove")
public class PrcatsRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Integer opaPrcatsNo = json2Pojo(request, Prcats.class).getOpaPrcatsNo();
		final Core core = new Core();
		if(opaPrcatsNo == null) {
			core.setMessage("查無優惠券ID，無法刪除");
			core.setSuccessful(false);
		}else {
			core.setSuccessful(SERVICE.remove(opaPrcatsNo));
		}
		writePojo2Json(response, core);
	}

}
