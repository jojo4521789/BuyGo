package web.back_end.opa.prod.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.opa.prod.entity.Prpics;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.opa.prod.util.PrpicsConstants.SERVICE;

@WebServlet("/opa/prpics/add")
public class PrpicsAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Prpics prpics = json2Pojo(request, Prpics.class);
		if(prpics == null) {
			prpics = new Prpics();
			prpics.setMessage("無商品照片資訊");
			prpics.setSuccessful(false);
			writePojo2Json(response, prpics);
		}
		prpics = SERVICE.add(prpics);
		writePojo2Json(response, prpics);
	}
}
