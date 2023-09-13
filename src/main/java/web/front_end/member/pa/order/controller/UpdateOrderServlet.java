package web.front_end.member.pa.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.front_end.member.pa.order.DTO.UpdateOrderDTO;
import web.front_end.member.pa.order.service.PaSoService;
import web.front_end.member.pa.order.service.impl.PaSoServiceImpl;

@WebServlet("/needLoginApi/UpdateOrder")
public class UpdateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaSoService service = new PaSoServiceImpl();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("執行更新訂單servlet 1");
		doPost(req, resp); 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("執行更新訂單servlet 2");
		UpdateOrderDTO updateOrderDTO = CommonUtil.json2Pojo(req, UpdateOrderDTO.class);
		if (updateOrderDTO == null) { // 如果不是從前端傳來updateOrderDTO, 是從綠界傳來付款成功
			UpdateOrderDTO newDTO = new UpdateOrderDTO();
			newDTO.setPaSoNo(Integer.parseInt(req.getParameter("paSoNo")) );
			newDTO.setNewStatus((byte)1);
			service.updateOrder(newDTO);
		} else {
			service.updateOrder(updateOrderDTO);
		}
	}
	
	private void fixHeaders(HttpServletResponse resp) {
		// 允許跨域請求的域名，可以是多個域名，用逗號分隔
		resp.setHeader("Access-Control-Allow-Origin", "https://payment-stage.ecpay.com.tw");
		// 允許的HTTP方法
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST");
		// 允許的自定義請求頭
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		// 預檢請求的緩存時間，單位為秒
		resp.setHeader("Access-Control-Max-Age", "3600");
	}
}
