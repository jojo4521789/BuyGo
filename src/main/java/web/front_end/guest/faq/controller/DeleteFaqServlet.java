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

@WebServlet("/front_end/guest/faq/DeletFaq")
public class DeleteFaqServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html:charset=utf-8");
		Faq faq = json2Pojo(req,Faq.class);
//		System.out.println(faq);
		if(SERVICE.remove(faq.getFaqNo())) {
			System.out.println("succes");
			writePojo2Json(resp, "yes");

		}else {
			System.out.println("unsucces");
			writePojo2Json(resp, "false");

		}
	}
	
}
