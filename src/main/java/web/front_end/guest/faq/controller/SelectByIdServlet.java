package web.front_end.guest.faq.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import static web.front_end.guest.faq.util.FaqConstants.SERVICE;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.entity.Core;
import web.front_end.guest.faq.entity.Faq;

@WebServlet("/api/front_end/guest/faq/SelectById")
public class SelectByIdServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html:charset=utf-8");
		Faq faq = json2Pojo(req, Faq.class);
//		System.out.println(faq.getFaqTitle());
		List<Faq> faqs = SERVICE.show(faq.getFaqTitle());
//		if(faqs.size() == 0) {
//			writePojo2Json(resp, null);
//		}
		if(faqs.size() != 0) {
			writePojo2Json(resp, faqs);
//			System.out.println("OK");
		}
		
	}
	
}
