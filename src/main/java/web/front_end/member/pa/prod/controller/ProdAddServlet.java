package web.front_end.member.pa.prod.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.pa.prod.util.ProdConstants.SERVICE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.pa.prod.entity.Prod;

@WebServlet("/front_end/member/pa/prod/ProdAddServlet")
public class ProdAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Prod prod = json2Pojo(request, Prod.class);
		
		if(prod == null) {
			prod = new Prod();
			prod.setMessage("無商品資訊");
			prod.setSuccessful(false);
			writePojo2Json(response, prod);
		}
		
		prod = SERVICE.add(prod);
		writePojo2Json(response, prod);
	}
}
