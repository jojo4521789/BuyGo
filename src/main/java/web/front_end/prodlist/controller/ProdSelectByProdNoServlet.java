package web.front_end.prodlist.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.prodlist.util.ProdlistConstants.SERVICE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.pa.prod.entity.PaProd;


@WebServlet("/api/front_end/paProdInfo")
public class ProdSelectByProdNoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	
	//查詢單筆訂單詳細資訊
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		Integer paProdNo = json2Pojo(request, PaProd.class).getPaProdNo();
		
		PaProd paProdlist = SERVICE.ProdInfoSelectByNo(paProdNo);
		writePojo2Json(response, paProdlist);
		
	}

}
