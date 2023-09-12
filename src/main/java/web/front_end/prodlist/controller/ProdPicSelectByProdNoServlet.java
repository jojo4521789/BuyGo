package web.front_end.prodlist.controller;

import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.prodlist.util.PaProdPicConstants.SERVICE;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.pa.prodpic.entity.ProdPic;

@WebServlet("/api/front_end/prodDetailPic")
public class ProdPicSelectByProdNoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		response.setContentType("text/html;charset=utf-8");
		
		List<ProdPic> paProdPicList = SERVICE.selectByPaProdId((Integer)(session.getAttribute("paProdNo")));
		writePojo2Json(response, paProdPicList);
	}
	
	

}
