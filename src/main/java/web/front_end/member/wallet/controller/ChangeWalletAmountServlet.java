package web.front_end.member.wallet.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.member.wallet.util.WalletMemberConstants.SERVICE;

import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.front_end.member.wallet.dto.ChangeWalletAmountDTO;

@WebServlet("/needLoginApi/member/wallet/changeWalletAmount")
public class ChangeWalletAmountServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		HttpSession session = request.getSession(); // 取得當前請求的Session
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		ChangeWalletAmountDTO changeWalletAmountDTO = json2Pojo(request, ChangeWalletAmountDTO.class);
		changeWalletAmountDTO.setMemberNo((Integer)session.getAttribute("memberNo"));
		
		// 新增資料進WalletTransHist Table開始
//		WalletTransHist walletTransHist = new WalletTransHist();
//		walletTransHist.setMemberNo(deductionWalletAmountDTO.getMemberNo());
//		walletTransHist.setWalletDetail(deductionWalletAmountDTO.getWalletDetail()); // 設置備註內容
//		walletTransHist.setWalletAmount(deductionWalletAmountDTO.getDeductionAmount());
//		
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 指定日期格式
//		String currentTimeString = dateFormat.format(new Date());
//		Timestamp currentTime = Timestamp.valueOf(currentTimeString);
//		walletTransHist.setWalletTime(currentTime); // 設置目前時間
		
		Double memberCurrentWalletAmount = SERVICE.loadMemberWalletAmountByMemberNo(changeWalletAmountDTO.getMemberNo()); // 查詢前端欲更改錢包金額的會員的目前錢包金額
		
//		if(memberCurrentWalletAmount < deductionWalletAmountDTO.getDeductionAmount()) { // 如果該會員錢包餘額小於要扣款的金額
//			deductionWalletAmountDTO.setMessage("錢包餘額不足，目前錢包餘額：" + memberCurrentWalletAmount);
//			deductionWalletAmountDTO.setSuccessful(false);
//			writePojo2Json(response, deductionWalletAmountDTO);
//			return;
//		}
//		walletTransHist.setWalletStatus(1); // 錢包提出
//		boolean addWalletTransHistIsSuccess = SERVICE.addWalletTransHist(walletTransHist);
		// 新增資料進WalletTransHist Table結束
		
		// 修改錢包金額至member Table
		boolean modifyWalletAmountIsSuccess = SERVICE.modifyWalletAmountByMemberNoAndWalletAmount(changeWalletAmountDTO.getMemberNo(), (memberCurrentWalletAmount + changeWalletAmountDTO.getWalletAmount()));
		
		if(modifyWalletAmountIsSuccess) {
			changeWalletAmountDTO.setSuccessful(true); // 若處理動作皆成功，傳送true至前端
			changeWalletAmountDTO.setMessage("交易後的錢包餘額：" + (memberCurrentWalletAmount + changeWalletAmountDTO.getWalletAmount()));
		}
		
		writePojo2Json(response, changeWalletAmountDTO);
	}
}
