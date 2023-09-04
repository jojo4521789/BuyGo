package web.front_end.guest.faq.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.guest.faq.entity.Faq;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import static web.front_end.guest.faq.util.FaqConstants.SERVICE;

import java.io.IOException;
import java.util.List;
@WebServlet("/front_end/guest/faq/SelectAll")

public class SelectAllServlet extends HttpServlet{
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
		List<Faq> faqs = SERVICE.showall();
		
		if(faqs.size()!=0) {
			writePojo2Json(resp, faqs);
		}
	}

}
