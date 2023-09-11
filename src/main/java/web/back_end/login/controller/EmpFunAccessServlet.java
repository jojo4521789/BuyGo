package web.back_end.login.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import core.util.HibernateUtil;

@WebServlet("/EmpLoginApi/back_end/EmpFunAccess")
public class EmpFunAccessServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		HttpSession httpSession = request.getSession();
		
		httpSession.getAttribute("funNo");
		System.out.println(httpSession.getAttribute("funNo"));
		
		response.setCharacterEncoding("UTF-8");
		
		
		
		
		
		
		
	}

}
