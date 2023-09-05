package web.front_end.member.pa.prod.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.pa.prod.util.ProdConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.pa.prod.entity.PaProd;

@WebServlet("/api/front_end/member/pa/prod/ProdAddServlet")
public class ProdAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
	    response.setCharacterEncoding("UTF-8");
	    PaProd prod = json2Pojo(request, PaProd.class);

		SERVICE.insert(prod);
		//successful
		System.out.println("prod.isSuccessful():" + prod.isSuccessful());
		if(prod.isSuccessful()) {
			System.out.println("修改成功");
			writePojo2Json(response, prod);
		}else {
			System.out.println("修改失敗");
			writePojo2Json(response, prod);
		}
		
	}
}
