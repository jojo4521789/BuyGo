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

@WebServlet("/front_end/guest/faq/EditFaq")
public class EditFaqServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html:charset=utf-8");
		Faq faq = json2Pojo(req,Faq.class);
		writePojo2Json(resp, SERVICE.update(faq));
//		System.out.println(resp+"111");
		System.out.println(faq.getMessage());
	}
	
}
