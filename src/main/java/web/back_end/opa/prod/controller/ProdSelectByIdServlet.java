package web.back_end.opa.prod.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.opa.prod.util.ProdConstants.SERVICE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.opa.prod.entity.Prod;

@WebServlet("/opa/prod/selectById")
public class ProdSelectByIdServlet extends HttpServlet {
	private static final long serialVersionUID = -2109925517241280273L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		Integer opaProdNo = json2Pojo(req, Prod.class).getOpaProdNo();
		Prod prod = SERVICE.prodSelectById(opaProdNo);
		writePojo2Json(resp, prod);
	}
	
}
