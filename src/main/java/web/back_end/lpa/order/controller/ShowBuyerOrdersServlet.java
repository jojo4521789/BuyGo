package web.back_end.lpa.order.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.back_end.lpa.order.entity.LpaSo;
import web.back_end.lpa.order.service.LpaSoService;
import web.back_end.lpa.order.service.impl.LpaSoServiceImpl;
import web.back_end.lpa.product.entity.LpaProd;

@WebServlet("/needLoginApi/loadLpaBuyerOrder")
public class ShowBuyerOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LpaSoService service = new LpaSoServiceImpl();
	CommonUtil commonUtil = new CommonUtil();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().setAttribute("memberNo", 3);
		Integer memberNo = (Integer)req.getSession().getAttribute("memberNo"); // 取得登入會員資訊
		Byte status = Byte.parseByte(req.getParameter("page"));
//		if (status == 0) {
//			commonUtil.writePojo2Json(resp, service.findAll(memberNo));
//		} else {
			commonUtil.writePojo2Json(resp, service.findByOrderStatus(memberNo, status));
//		}
		
	}

}
