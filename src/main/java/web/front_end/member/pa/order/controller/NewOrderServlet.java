package web.front_end.member.pa.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.front_end.member.pa.order.DTO.CheckOutDTO;
import web.front_end.member.pa.order.entity.PaSo;
import web.front_end.member.pa.order.entity.PaSoDetails;
import web.front_end.member.pa.order.service.PaSoService;
import web.front_end.member.pa.order.service.impl.PaSoServiceImpl;

@WebServlet("/needLoginApi/newOrder")
public class NewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaSoService paSoService = new PaSoServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer memberNo = (Integer) req.getSession().getAttribute("memberNo"); // 取得登入會員(買家)資訊
		
		CheckOutDTO checkOutDTO =  CommonUtil.json2Pojo(req, CheckOutDTO.class);
		System.out.println(checkOutDTO.getPaSo().getPaRecName());
		System.out.println(checkOutDTO.getPaSoDetails()); // 回傳List<LpaSoDetails>
		PaSo paSo = checkOutDTO.getPaSo();
		List<PaSoDetails> paSoDetailsList = checkOutDTO.getPaSoDetails();
		
		CommonUtil.writePojo2Json(resp, paSoService.generateNewOrder(memberNo, paSo, paSoDetailsList));
	}
} 
