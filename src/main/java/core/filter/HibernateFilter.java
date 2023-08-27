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

@WebFilter("/api/*")
public class HibernateFilter extends HttpFilter {
	/* 該Filter在/api/內的Api觸發前後執行，用於執行交易前操作交易機制的開關 */
	/*/api/內的Api應放入不需登入即可操作的api */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException {
		
		String path = req.getRequestURI(); // 取得客戶請求的URI
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
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