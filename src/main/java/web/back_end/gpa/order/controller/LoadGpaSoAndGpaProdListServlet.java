package web.back_end.gpa.order.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.seller.gpa.order.util.GpaSoConstants.SERVICE;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.seller.gpa.order.dto.GpaSoAndGpaProdDTO;

@WebServlet("/api/back_end/loadGpaSoAndGpaProdList") // 後台會員登入加入後記得改
public class LoadGpaSoAndGpaProdListServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession(); // 取得當前請求的Session
		response.setCharacterEncoding("UTF-8");
		GpaSoAndGpaProdDTO gpaSoAndGpaProdDTO = json2Pojo(request, GpaSoAndGpaProdDTO.class);
		
		List<GpaSoAndGpaProdDTO> gpaSoAndGpaProdDTOList = SERVICE.loadGpaSoAndGpaProdDTOByGpaSoAndGpaProd(gpaSoAndGpaProdDTO.getGpaSo(), gpaSoAndGpaProdDTO.getGpaProd());
		
		writePojo2Json(response, gpaSoAndGpaProdDTOList);
	}
}
