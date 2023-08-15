package web.back_end.opa.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.opa.prod.entity.Prod;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.opa.prod.util.ProdConstants.SERVICE;

@WebServlet("/prod/manage")
public class ProdManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		List<Prod> prodList = SERVICE.findAll();
		writePojo2Json(response, prodList);
	}

}
