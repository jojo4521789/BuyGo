package web.front_end.member.eva.controller;

import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.eva.util.EvaMemberConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.acc.entity.Member;
import web.front_end.member.eva.dto.MemberSellerEvaStarAndCountDTO;

@WebServlet("/needLoginApi/front_end/loadMemberSellerEvaStarAndCount")
public class LoadMemberSellerEvaStarAndCountServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // 取得當前請求的Session
		response.setCharacterEncoding("UTF-8");
		Member member = SERVICE.loadMemberByMemberNo((Integer)(session.getAttribute("memberNo")));
		
		MemberSellerEvaStarAndCountDTO memberSellerEvaStarAndCountDTO = new MemberSellerEvaStarAndCountDTO();
		memberSellerEvaStarAndCountDTO.setSellerEvaStar(member.getSellerEvaStar());
		memberSellerEvaStarAndCountDTO.setSellerEvaCount(member.getSellerEvaCount());
		
		writePojo2Json(response, memberSellerEvaStarAndCountDTO);
	}
}
