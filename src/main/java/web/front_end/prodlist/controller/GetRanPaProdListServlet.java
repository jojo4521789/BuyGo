package web.front_end.prodlist.controller;

import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.prodlist.util.ProdlistConstants.SERVICE;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.prodlist.entity.PaProdlist;


@WebServlet("/api/front_end/ramPaProdInfo")
public class GetRamPaProdListServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/html;charset=utf-8");
		
		List<PaProdlist> randomProdList = SERVICE.RamgetProdNo(8);
		writePojo2Json(response, randomProdList);
	}

}
