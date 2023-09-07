package web.front_end.member.coupon.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.back_end.opa.coupon.entity.CpOwner;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.opa.coupon.util.CpOwnerConstants.SERVICE;

@WebServlet("/needLoginApi/opa/coupon/getMemberCoupon")
public class MemberCouponServlet extends HttpServlet{
	private static final long serialVersionUID = -4177241683847784138L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession(); // 取得當前請求的Session
		Integer memberNo = (Integer)(session.getAttribute("memberNo"));
		List<CpOwner> cpOwnerList = SERVICE.selectByMember(memberNo);
		writePojo2Json(resp, cpOwnerList);
	}
}
