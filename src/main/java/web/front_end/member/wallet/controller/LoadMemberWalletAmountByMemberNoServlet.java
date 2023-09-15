package web.front_end.member.wallet.controller;

import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.member.wallet.util.WalletMemberConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.acc.entity.Member;

@WebServlet("/needLoginApi/member/wallet/loadMemberWalletAmountByMemberNo")
public class LoadMemberWalletAmountByMemberNoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // 取得當前請求的Session
		response.setCharacterEncoding("UTF-8");
		
		
		Double memberWalletAmount = SERVICE.loadMemberWalletAmountByMemberNo((Integer)session.getAttribute("memberNo"));
		
		Member member = new Member();
		member.setMemberWalletAmount(memberWalletAmount);
		
		System.out.println("ok");
		
		writePojo2Json(response, member);
	}
}
