package web.front_end.seller.gpa.prod.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/front_end/seller/gpa/prod/loadGpaProd")
public class LoadGpaProdServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		
	}
}
