package web.back_end.opa.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.opa.prod.entity.Prcats;

import static web.back_end.opa.prod.util.PrcatsConstants.SERVICE;
import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

@WebServlet("/api/opa/prcats/search")
public class PrcatsSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String input = json2Pojo(request, Prcats.class).getOpaPrcatsName();
		List<Prcats> prcatsList = SERVICE.findPart(input);
		writePojo2Json(response, prcatsList);
	}
}
