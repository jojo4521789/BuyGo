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

@WebServlet("/api/opa/prod/prodUpdateStatus")
public class ProdUpdateStatusServlet extends HttpServlet{
	private static final long serialVersionUID = -1763833702833965510L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		Prod prod = json2Pojo(req, Prod.class);
		writePojo2Json(resp, SERVICE.updateProdStatus(prod));
	}
}
