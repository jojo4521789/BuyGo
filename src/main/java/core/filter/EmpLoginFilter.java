package core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import core.util.HibernateUtil;

@WebFilter("/EmpLoginApi/*")
public class EmpLoginFilter extends HttpFilter{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException {
		
		String path = req.getRequestURI(); // 取得客戶請求的URI
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		HttpSession httpSession = req.getSession(); // 取得當前請求的Session
		if(httpSession.getAttribute("sessionId") == null) { // 如果會員不為登入狀態
			System.out.println("LoginFilter:使用者不為登入狀態, sessionid未被授權, sessionid:" + httpSession.getAttribute("sessionId"));
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 向前端發送401狀態，前端收到後則跳轉頁面至登入頁面(JavaScript需撰寫跳轉動作)
		}
		else {
			System.out.println("LoginFilter:使用者為登入狀態empNo:" + httpSession.getAttribute("empNo")+ "使用者為登入狀態FunNo:" + httpSession.getAttribute("funNo") + ", sessionid:" + httpSession.getAttribute("sessionId"));
		}
		
		try {
			Transaction transaction = session.beginTransaction();
			chain.doFilter(req, res);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}
}

