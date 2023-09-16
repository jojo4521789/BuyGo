package web.back_end.opa.prod.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.entity.Core;
import web.back_end.opa.prod.entity.Prod;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.opa.prod.util.ProdConstants.SERVICE;

@WebServlet("/api/opa/prod/remove")
public class ProdRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Integer opaProdNo = json2Pojo(request, Prod.class).getOpaProdNo();
		final Core core = new Core();
		if(opaProdNo == null) {
			core.setMessage("查無商品ID，無法刪除");
			core.setSuccessful(false);
		}else {
			core.setSuccessful(SERVICE.remove(opaProdNo));
		}
		writePojo2Json(response, core);
	}

}
