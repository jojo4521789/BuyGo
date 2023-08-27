package web.back_end.opa.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.opa.prod.entity.Prpics;
import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.opa.prod.util.PrpicsConstants.SERVICE;

@WebServlet("/opa/prpics/selectByProd")
public class PrpicsSelectByProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		final Integer opaProdNo = json2Pojo(request, Prpics.class).getOpaProdNo();
		
		List<Prpics> prpicsList = SERVICE.SelectByProdId(opaProdNo);
		writePojo2Json(response, prpicsList);
	}

}
