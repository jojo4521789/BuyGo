package web.front_end.prodlist.controller;

import static core.util.CommonUtil.writePojo2Json;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.prodlist.entity.PaProdlist;


@WebServlet("/front_end/prodlist/*")
public class ShowProdlistServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	
	//查詢單筆訂單詳細資訊
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		PaProdlist prodlist = (PaProdlist) request.getSession().getAttribute("prodlist");
		if (prodlist == null) {
			prodlist = new PaProdlist();
			prodlist.setSuccessful(false);
		}else {
			prodlist.setSuccessful(true);
		}
		writePojo2Json(response, prodlist);
		
	}

}
