package web.front_end.member.pa.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import web.front_end.member.pa.order.DTO.NewReturnDTO;
import web.front_end.member.pa.order.entity.PaReturn;
import web.front_end.member.pa.order.service.PaSoService;
import web.front_end.member.pa.order.service.impl.PaSoServiceImpl;

@WebServlet("/needLoginApi/updateOrCancelReturn")
public class UpdateOrCancelReturnServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PaSoService paSoService = new PaSoServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Integer memberNo = (Integer) req.getSession().getAttribute("memberNo"); // 取得登入會員(買家)資訊

		PaReturn paReturn = CommonUtil.json2Pojo(req, PaReturn.class);
		
		
		CommonUtil.writePojo2Json(resp, paSoService.updateOrCancelReturn(paReturn));
	}
}
