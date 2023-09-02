package web.back_end.lpa.order.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.back_end.lpa.order.DTO.ShipOrCancelDTO;
import web.back_end.lpa.order.service.LpaSoService;
import web.back_end.lpa.order.service.impl.LpaSoServiceImpl;

@WebServlet("/needLoginApi/ShipOrCancelOrder")
public class ShipOrCancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LpaSoService service = new LpaSoServiceImpl();
	CommonUtil commonUtil = new CommonUtil();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		ShipOrCancelDTO shipOrCancelDTO = commonUtil.json2Pojo(req, ShipOrCancelDTO.class);
		System.out.println(shipOrCancelDTO.getNewStatus());
		System.out.println(shipOrCancelDTO.getLpaSoNo());
		
		service.ShipOrCancelOrder(shipOrCancelDTO);
		
	}
}
