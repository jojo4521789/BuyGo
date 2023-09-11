package web.front_end.seller.gpa.order.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.seller.gpa.order.util.GpaSoConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.seller.gpa.order.entity.GpaSo;

@WebServlet("/needLoginApi/front_end/updateGpaEvaMember")
public class UpdateGpaEvaMemberServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		GpaSo gpaSo= json2Pojo(request, GpaSo.class);
		boolean status = SERVICE.updateGpaEvaMemberByGpaSoNo(gpaSo.getGpaSoNo(), gpaSo.getGpaEvaMember());
		gpaSo.setSuccessful(status);
		writePojo2Json(response, gpaSo);
	}
}
