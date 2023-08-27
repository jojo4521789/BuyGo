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

@WebFilter("/needLoginApi/*")
public class LoginFilter extends HttpFilter {
	/* 該Filter在/needLoginApi/內的Api觸發前後執行，檢查當前的Session是否登入過，若不為登入狀態則向前端發送401，使前端跳轉頁面至登入頁面 */
	/*/needLoginApi/內的Api應放入需登入才可執行的api */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException {
		
		String path = req.getRequestURI(); // 取得客戶請求的URI
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		// 檢查客戶端當前Session ID
		HttpSession httpSession = req.getSession(); // 取得當前請求的Session
		if(httpSession.getAttribute("sessionId") == null) { // 如果會員不為登入狀態
			System.out.println("LoginFilter:使用者不為登入狀態, sessionid未被授權, sessionid:" + httpSession.getAttribute("sessionId"));
//            res.setContentType("application/json");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 向前端發送401狀態，前端收到後則跳轉頁面至登入頁面(JavaScript需撰寫跳轉動作)
		}
		else { // 如果會員為登入狀態
			System.out.println("LoginFilter:使用者為登入狀態,memberNo:" + httpSession.getAttribute("memberNo") + ", sessionid:" + httpSession.getAttribute("sessionId"));
		}
		
		// 交易機制開關
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