package web.front_end.guest.faq.controller;


import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.guest.faq.util.FaqConstants.SERVICE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import web.front_end.guest.faq.entity.Faq;


@WebServlet("/front_end/guest/faq/IncreaseFaq")
public class IncreaseFaqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html:charset=utf-8");
	    Faq faq = json2Pojo(req, Faq.class);
	    SERVICE.increase(faq);
	    
//	    System.out.println("Faq.isSuccessful():" + faq.isSuccessful());
	    if(faq.isSuccessful()) {
	    	writePojo2Json(resp,faq);
	    }else {
	    	writePojo2Json(resp, faq);
	    }
	}
	
}
