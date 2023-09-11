package web.front_end.member.pa.order.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.front_end.member.pa.order.DTO.ShipOrCancelDTO;
import web.front_end.member.pa.order.service.PaSoService;
import web.front_end.member.pa.order.service.impl.PaSoServiceImpl;

@WebServlet("/needLoginApi/ShipOrCancelOrder")
public class ShipOrCancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaSoService service = new PaSoServiceImpl();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		ShipOrCancelDTO shipOrCancelDTO = CommonUtil.json2Pojo(req, ShipOrCancelDTO.class);
		System.out.println(shipOrCancelDTO.getNewStatus());
		System.out.println(shipOrCancelDTO.getPaSoNo());
		
		service.ShipOrCancelOrder(shipOrCancelDTO);
		
	}
}
