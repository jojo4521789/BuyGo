package web.front_end.member.pa.req.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.pa.req.entity.MPaReq;

import static core.util.CommonUtil.writePojo2Json;

public class GetPaReqServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("fetch成功");
		
		MPaReq mpaReq = (MPaReq)request.getSession().getAttribute("paRqNo");
		
		if(mpaReq == null) {
			mpaReq = new MPaReq();
			mpaReq.setMessage("無此訂單編號資訊");
			mpaReq.setSuccessful(false);
		}else {
			mpaReq.setSuccessful(true);
		}
		
		writePojo2Json(response, mpaReq);
		
	}
	
	

}
