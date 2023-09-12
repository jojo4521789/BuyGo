package web.front_end.member.pa.req.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.pa.req.util.MPaReqConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.pa.req.entity.MPaReq;

@WebServlet("/api/front_end/seller/manager")
public class ManagePaReqServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		
		resp.setContentType("text/html;charset=utf-8");
		
		MPaReq mPaReq = json2Pojo(req, MPaReq.class);
		writePojo2Json(resp, SERVICE.updatePaReqStatus(mPaReq));
							
	}
	
}
