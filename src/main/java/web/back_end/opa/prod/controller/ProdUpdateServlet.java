package web.back_end.opa.prod.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.opa.prod.entity.Prod;
import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.opa.prod.util.ProdConstants.SERVICE;

@WebServlet("/opa/prod/update")
public class ProdUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		Prod prod = json2Pojo(request, Prod.class);
		writePojo2Json(response, SERVICE.update(prod));
	}

}
