package web.back_end.lpa.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.back_end.lpa.order.DTO.CheckOutDTO;
import web.back_end.lpa.order.entity.LpaSo;
import web.back_end.lpa.order.entity.LpaSoDetails;
import web.back_end.lpa.order.service.LpaSoService;
import web.back_end.lpa.order.service.impl.LpaSoServiceImpl;

@WebServlet("/needLoginApi/newOrder")
public class NewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LpaSoService lpaSoService = new LpaSoServiceImpl();
	CommonUtil commonUtil = new CommonUtil();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().setAttribute("memberNo", 3);
		Integer memberNo = (Integer) req.getSession().getAttribute("memberNo"); // 取得登入會員(買家)資訊
		
		CheckOutDTO checkOutDTO =  commonUtil.json2Pojo(req, CheckOutDTO.class);
		System.out.println(checkOutDTO.getLpaSo().getLpaRecName());
		System.out.println(checkOutDTO.getLpaSoDetails()); // 回傳List<LpaSoDetails>
		LpaSo lpaSo = checkOutDTO.getLpaSo();
		List<LpaSoDetails> lpaSoDetailsList = checkOutDTO.getLpaSoDetails();
		
		commonUtil.writePojo2Json(resp, lpaSoService.generateNewOrder(memberNo, lpaSo, lpaSoDetailsList));
	}
} 
