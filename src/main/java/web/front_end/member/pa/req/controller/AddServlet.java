package web.front_end.member.pa.req.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.pa.req.util.MPaReqConstants.SERVICE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.pa.req.entity.MPaReq;

@WebServlet("/needLoginApi/front_end/member/AddPaReq")

public class AddServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		System.out.println("fetch成功");
		
		MPaReq mPaReq = json2Pojo(request, MPaReq.class);
		
		if (mPaReq == null) {
			mPaReq = new MPaReq();
			mPaReq.setMessage("訂單有誤");
			mPaReq.setSuccessful(false);
			System.out.println("mPaReq.getMemberNoSeller()1:" + mPaReq.getMemberNoSeller());
			writePojo2Json(response, mPaReq);

		}
		System.out.println("節點");
		System.out.println("mPaReq.getMemberNoSeller()2:" + mPaReq.getMemberNoSeller());
		mPaReq = SERVICE.add(mPaReq);
		writePojo2Json(response, mPaReq);
		
		System.out.println("傳入成功");

	}
}
