package web.front_end.member.pa.prodpic.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.pa.prodpic.util.ProdPicConstants.SERVICE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.pa.prodpic.entity.ProdPic;

@WebServlet("/front_end/member/pa/prodpic/ProdPicAddServlet")
public class ProdPicAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdPic prodpic = json2Pojo(request, ProdPic.class);
		
		System.out.println(prodpic);
		
		if(prodpic == null) {
			prodpic = new ProdPic();
			prodpic.setMessage("無商品資訊");
			prodpic.setSuccessful(false);
			writePojo2Json(response, prodpic);
		}
		
		prodpic = SERVICE.add(prodpic);
		writePojo2Json(response, prodpic);
	}

}
