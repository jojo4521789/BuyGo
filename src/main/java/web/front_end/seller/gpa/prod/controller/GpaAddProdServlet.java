package web.front_end.seller.gpa.prod.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.seller.gpa.prod.util.GpaProdConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.seller.gpa.prod.entity.GpaProd;

@WebServlet("/api/front_end/member/pa/prod/GpaAddProdServlet")
public class GpaAddProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
	    response.setCharacterEncoding("UTF-8");
	    GpaProd gpaProd = json2Pojo(request, GpaProd.class);

		SERVICE.insert(gpaProd);
		//successful
		System.out.println("gpaProd.isSuccessful():" + gpaProd.isSuccessful());
		if(gpaProd.isSuccessful()) {
			System.out.println("修改成功");
			writePojo2Json(response, gpaProd);
		}else {
			System.out.println("修改失敗");
			writePojo2Json(response, gpaProd);
		}
		
	}

}
