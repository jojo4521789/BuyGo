package web.back_end.opa.req.controller;

import static web.back_end.opa.req.util.OpaRequestConstants.SERVICE;
import static core.util.CommonUtil.writePojo2Json;
import web.back_end.opa.req.service.impl.OpaRequestServiceImpl;
import core.entity.Core;
import java.io.IOException;
import java.util.List;
import java.sql.Timestamp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.back_end.opa.req.entity.OpaRequest;
import web.back_end.opa.req.entity.Request;

@WebServlet("/needLoginApi/member/opa/request")
public class MemberRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String [] REQUEST_STATUS_MAPPING = new String []{ "待審核", "審核通過", "審核未通過" };

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Integer memberNo = (Integer)(session.getAttribute("memberNo"));
		List<OpaRequest> requests = SERVICE.findAllRequestsByMember(memberNo);
		response.setCharacterEncoding("UTF-8");
		Request[] requestsArray = new Request[requests.size()];
		for (int i = 0; i < requests.size(); i++) {
			Request opaRequest = new Request();
			opaRequest.setOpaRequestNo(requests.get(i).getOpaRequestNo());
			opaRequest.setOpaRequestProductsName(requests.get(i).getOpaRequestProductsName());
			opaRequest.setOpaRequestProductsUrl(requests.get(i).getOpaRequestProductsUrl());
			opaRequest.setOpaRequestProductsContent(requests.get(i).getOpaRequestProductsContent());
			opaRequest.setOpaRequestStatus(REQUEST_STATUS_MAPPING[requests.get(i).getOpaRequestStatus()]);
			opaRequest.setOpaRequestStartdate(requests.get(i).getOpaRequestStartdate());
			 if(requests.get(i).getOpaFailedReason() != null)
                opaRequest.setOpaFailedReason(OpaRequestServiceImpl.failedReasonMap[requests.get(i).getOpaFailedReason()]);
            else
                opaRequest.setOpaFailedReason("-");
            requestsArray[i] = opaRequest;
        }

		writePojo2Json(response, requestsArray);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Integer memberNo = (Integer)(session.getAttribute("memberNo"));
		String opaRequestProductsName = request.getParameter("opaRequestProductsName");
		String opaRequestProductsUrl = request.getParameter("opaRequestProductsUrl");
		String opaRequestProductsContent = request.getParameter("opaRequestProductsContent");
		OpaRequest opaRequest = new OpaRequest();
		opaRequest.setMemberNo(memberNo);
		opaRequest.setOpaRequestProductsName(opaRequestProductsName);
		opaRequest.setOpaRequestProductsUrl(opaRequestProductsUrl);
		opaRequest.setOpaRequestProductsContent(opaRequestProductsContent);
		opaRequest.setOpaRequestStatus((byte)0);
		opaRequest.setOpaRequestStartdate(new Timestamp(System.currentTimeMillis()));
		int id = SERVICE.save(opaRequest);
		
		final Core core = new Core();
		core.setSuccessful(id > 0);
		writePojo2Json(response, core);
	}
}