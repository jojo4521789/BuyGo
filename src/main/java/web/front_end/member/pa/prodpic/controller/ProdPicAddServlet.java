package web.front_end.member.pa.prodpic.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.pa.prodpic.util.ProdPicConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.pa.prodpic.entity.ProdPic;

@WebServlet("/api/front_end/member/pa/prodpic/ProdPicAddServlet")
public class ProdPicAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		doPost(request, response);
	}
	@Override   
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		ProdPic prodpic = json2Pojo(request, ProdPic.class);
		
		System.out.println(prodpic);
		
		SERVICE.insert(prodpic);
		//successful
		System.out.println("prodpic.isSuccessful():" + prodpic.isSuccessful());
		if(prodpic.isSuccessful()) {
			System.out.println("修改成功");
			writePojo2Json(response, prodpic);
		}else {
			System.out.println("修改失敗");
			writePojo2Json(response, prodpic);
		}
	}

}
