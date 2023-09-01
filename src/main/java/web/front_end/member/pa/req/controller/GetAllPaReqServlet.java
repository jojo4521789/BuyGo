package web.front_end.member.pa.req.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.pa.req.util.MPaReqConstants.SERVICE;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.pa.req.entity.MPaReq;

@WebServlet("/api/front_end/member/getAllPaReq")
public class GetAllPaReqServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) {

		res.setCharacterEncoding("UTF-8");
		MPaReq mPaReq = json2Pojo(req, MPaReq.class);

		List<MPaReq> mPaReqs = SERVICE.loadReqListByMemberNo(mPaReq.getMemberNoMember());
		if (mPaReqs.size() != 0) {
			writePojo2Json(res, mPaReqs);

		}

	}
}
