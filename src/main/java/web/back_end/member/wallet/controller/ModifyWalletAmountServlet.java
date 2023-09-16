package web.back_end.member.wallet.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.back_end.member.wallet.util.WalletMemberConstants.SERVICE;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.back_end.member.wallet.entity.BackEndWalletTransHist;
import web.front_end.member.acc.entity.Member;

@WebServlet("/api/back_end/modifyWalletAmount") // 後台會員登入加入後記得改
public class ModifyWalletAmountServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
//		HttpSession session = request.getSession(); // 取得當前請求的Session
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		Member member = json2Pojo(request, Member.class);
		
		// 新增資料進WalletTransHist Table開始
		BackEndWalletTransHist walletTransHist = new BackEndWalletTransHist();
		walletTransHist.setMemberNo(member.getMemberNo());
		walletTransHist.setWalletDetail(member.getMessage()); // 設置備註內容
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 指定日期格式
		String currentTimeString = dateFormat.format(new Date());
//		System.out.println("currentTimeString:" + currentTimeString);
		Timestamp currentTime = Timestamp.valueOf(currentTimeString);
		walletTransHist.setWalletTime(currentTime); // 設置目前時間
		
		Double memberCurrentWalletAmount = SERVICE.loadMemberWalletAmountByMemberNo(member.getMemberNo()); // 查詢前端欲更改錢包金額的會員的目前錢包金額
		walletTransHist.setWalletAmount(Math.abs(member.getMemberWalletAmount() - memberCurrentWalletAmount)); // 設置交易金額(目前金額-改動金額再取絕對值)
		if(member.getMemberWalletAmount() >= memberCurrentWalletAmount) { 
			walletTransHist.setWalletStatus(0); //若欲改動金額大於目前錢包金額，則為"存入"
		}
		else {
			walletTransHist.setWalletStatus(1); //若欲改動金額小於目前錢包金額，則為"提出"
		}
		boolean addWalletTransHistIsSuccess = SERVICE.addWalletTransHist(walletTransHist);
		// 新增資料進WalletTransHist Table結束
		
		// 修改錢包金額至member Table
		boolean modifyWalletAmountIsSuccess = SERVICE.modifyWalletAmountByMemberNoAndWalletAmount(member.getMemberNo(), member.getMemberWalletAmount());
		
		if(addWalletTransHistIsSuccess && modifyWalletAmountIsSuccess) {
			member.setSuccessful(true); // 若處理動作皆成功，傳送true至前端
		}
		else {
			member.setSuccessful(false);
		}
		
		writePojo2Json(response, member);
	}
}
