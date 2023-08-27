package web.back_end.opa.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.opa.prod.entity.Prod;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.opa.prod.util.ProdConstants.SERVICE;

@WebServlet("/opa/prod/selectByName")
public class ProdSelectByNameServlet extends HttpServlet {
	private static final long serialVersionUID = -481688560165943628L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String input = json2Pojo(req, Prod.class).getOpaProdName();
		List<Prod> prodList = SERVICE.findPart(input);
		writePojo2Json(resp, prodList);
	}
	
}
