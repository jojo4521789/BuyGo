package web.front_end.member.pa.req.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.pa.req.util.MPaReqConstants.SERVICE;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.front_end.member.pa.req.dto.JoinSellerNameDTO;
import web.front_end.member.pa.req.entity.MPaReq;

@WebServlet("/needLoginApi/front_end/member/getAllPaReq")
public class GetAllPaReqServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

		resp.setCharacterEncoding("UTF-8");
		MPaReq mPaReq = json2Pojo(req,  MPaReq.class);

		List<MPaReq> mPaReqs = SERVICE.loadReqListByMemberNo(mPaReq.getMemberNoMember());
		
		List<JoinSellerNameDTO> joinSellerNameDTOList = new LinkedList<JoinSellerNameDTO>();
		for(MPaReq mPaReq_1 : mPaReqs) {
			JoinSellerNameDTO joinSellerNameDTO = new JoinSellerNameDTO();
			//列出所需欄位
			joinSellerNameDTO.setPaRqNo(mPaReq_1.getPaRqNo());
			joinSellerNameDTO.setMemberNoSeller(mPaReq_1.getMemberNoSeller());
			joinSellerNameDTO.setMemberAcct(mPaReq_1.getMember().getMemberAcct());
			joinSellerNameDTO.setPaRqProdName(mPaReq_1.getPaRqProdName());
			joinSellerNameDTO.setPaRqUrl(mPaReq_1.getPaRqUrl());
			joinSellerNameDTO.setPaRqPrice(mPaReq_1.getPaRqPrice());
			joinSellerNameDTO.setPaRqQty(mPaReq_1.getPaRqQty());
			joinSellerNameDTO.setPaRqNote(mPaReq_1.getPaRqNote());
			joinSellerNameDTO.setPaRqStat(mPaReq_1.getPaRqStat());
			joinSellerNameDTO.setPaRqStartDate(mPaReq_1.getPaRqStartDate());
			
			joinSellerNameDTOList.add(joinSellerNameDTO);
			joinSellerNameDTO = null;
		}
		
		
//		System.out.println("mPaReqs.size():" + mPaReqs.size());
//		for(MPaReq mPaReq1 : mPaReqs) {
//			System.out.println(mPaReq1.getPaRqNo());
//			System.out.println(mPaReq1.getMember().getMemberAcct());
//		}
		
		if (joinSellerNameDTOList.size() != 0) {
			writePojo2Json(resp, joinSellerNameDTOList);

		}

	}
}
