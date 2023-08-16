package web.back_end.opa.prod.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.opa.prod.entity.Prcats;

import static web.back_end.opa.prod.util.PrcatsConstants.SERVICE;
import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

@WebServlet("/opa/prcats/add")
public class PrcatsAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Prcats prcats = json2Pojo(request, Prcats.class);
		
		if(prcats == null) {
			prcats = new Prcats();
			prcats.setMessage("無商品類別資訊");
			prcats.setSuccessful(false);
			writePojo2Json(response, prcats);
		}
		
		prcats = SERVICE.add(prcats);
		writePojo2Json(response, prcats);
	}

}
