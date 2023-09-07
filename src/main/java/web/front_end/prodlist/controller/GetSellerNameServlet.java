package web.front_end.prodlist.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.prodlist.util.ProdlistSellerConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.acc.entity.Member;


@WebServlet("/api/front_end/GetSellerName")
public class GetSellerNameServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/html;charset=utf-8");
		
		Integer memberNo = json2Pojo(request, Member.class).getMemberNo();
		
		Member member = SERVICE.getSellerName(memberNo);
		writePojo2Json(response, member);
		

		
	}

}
