package web.front_end.guest.prod.opa.controller;

import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.opa.prod.util.ProdConstants.SERVICE;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.opa.prod.entity.Prod;

@WebServlet("/api/opa/prod/getOnShelfProds")
public class opaProdServlet extends HttpServlet{
	private static final long serialVersionUID = -5167941435301333258L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		List<Prod> prodList = SERVICE.getOnOffShelfProds(1);
		writePojo2Json(resp, prodList);
	}
}
