package web.front_end.seller.gpa.order.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.seller.gpa.order.util.GpaSoConstants.SERVICE;

import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.seller.gpa.order.dto.GpaSoCountDTO;
import web.front_end.seller.gpa.order.dto.GpaSoSearchPageDTO;

@WebServlet("/needLoginApi/front_end/loadGpaSoCountBySellerMemberNoAndSoStatAndSearchStr")
public class LoadGpaSoCountBySellerMemberNoAndSoStatAndSearchStrServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		HttpSession session = request.getSession(); // 取得當前請求的Session
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		GpaSoSearchPageDTO gpaSoSearchPageDTO= json2Pojo(request, GpaSoSearchPageDTO.class);
		
		Integer gpaSoCount = SERVICE.loadGpaSoCountBySellerMemberNoAndSoStatAndSearchStr((Integer)(session.getAttribute("memberNo")), gpaSoSearchPageDTO.getGpaSoStat(), gpaSoSearchPageDTO.getSearchStr());
		GpaSoCountDTO gpaSoCountDTO = new GpaSoCountDTO();
		gpaSoCountDTO.setGpaSoCount(gpaSoCount);
		
		writePojo2Json(response, gpaSoCountDTO);
	}
}
