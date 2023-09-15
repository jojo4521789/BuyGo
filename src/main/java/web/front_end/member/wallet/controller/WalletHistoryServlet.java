package web.front_end.member.wallet.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.member.util.SHA256EncoderUtil.SHA256Encode;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static web.front_end.member.wallet.util.OpaWalletConstants.SERVICE;

import web.back_end.member.wallet.service.WalletMemberService;
import web.back_end.member.wallet.service.impl.WalletMemberServiceImpl;
import web.front_end.member.acc.entity.Member;
import web.front_end.member.wallet.dto.ChangeWalletAmountDTO;
import web.front_end.member.wallet.entity.WalletTransHist;


@WebServlet("/needLoginApi/member/wallet/history")
public class WalletHistoryServlet extends HttpServlet {
    static final String PAGE_URL = "/front_end/pages/member/wallet.html";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int memberNo = (Integer)session.getAttribute("memberNo");
        List<WalletTransHist> walletTransHistList = SERVICE.findAllByMember(memberNo);
		response.setCharacterEncoding("UTF-8");
        writePojo2Json(response, walletTransHistList);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect(request.getContextPath() + PAGE_URL);
            return;
        }
        HttpSession session = request.getSession();
        int memberNo = (Integer)session.getAttribute("memberNo");
        int value;
        try {
            value = Integer.parseInt(request.getParameter("value"));
            if (value <= 0) {
                response.sendRedirect(request.getContextPath() + PAGE_URL + "?message=%E9%87%91%E9%A1%8D%E9%8C%AF%E8%AA%A4");
                return;
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + PAGE_URL + "?message=%E9%87%91%E9%A1%8D%E9%8C%AF%E8%AA%A4");
            return;
        }
        request.setAttribute("action", action);
        request.setAttribute("value", value);

        if (action.equals("add")) {
            add(request, response, action, value, memberNo);
        } else if (action.equals("withdraw")) {
            withdraw(request, response, action, value, memberNo);
        } else {
            response.sendRedirect(request.getContextPath() + PAGE_URL);
        }
    }

    private boolean isExpired(String expiryDate) {
        if (expiryDate == null) {
            return true;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int currentYear = calendar.get(Calendar.YEAR);
        currentYear = currentYear % 100;
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        String[] split = expiryDate.split("/");
        int expiryYear = Integer.parseInt(split[1]);
        int expiryMonth = Integer.parseInt(split[0]);
        if (expiryYear < currentYear) {
            return true;
        }
        if (expiryYear == currentYear && expiryMonth < currentMonth) {
            return true;
        }
        return false;
    }

    private boolean checkCreditCard(String cardNumber, String expiryDate, String cvv) {
        if (cardNumber == null || expiryDate == null || cvv == null) {
            return false;
        }
        if (cardNumber.length()!= 16) {
            return false;
        }
        if (expiryDate.length()!= 5) {
            return false;
        }
        if (isExpired(expiryDate)) {
            return false;
        }
        if (cvv.length()!= 3) {
            return false;
        }
        return true;
    }

    private void add(HttpServletRequest request, HttpServletResponse response, String action, int value, int memberNo)
            throws ServletException, IOException {
        String cardNumber = request.getParameter("cardNumber");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");
        if (!checkCreditCard(cardNumber, expiryDate, cvv)) {
            response.sendRedirect(request.getContextPath() + PAGE_URL + "?message=%E4%BF%A1%E7%94%A8%E5%8D%A1%E8%AA%8D%E8%AD%89%E5%A4%B1%E6%95%97");
            return;
        }

        WalletTransHist walletTransHist = new WalletTransHist();
        walletTransHist.setMemberNo(memberNo);
        walletTransHist.setWalletDetail("儲值" + value + "元");
        walletTransHist.setWalletStatus((byte) 1);
        walletTransHist.setWalletAmount(value);
        SERVICE.saveOrUpdate(walletTransHist);
        
        changeWalletAmount(request, response, (double)value);
        System.out.println("執行至此1");
        
        response.sendRedirect(request.getContextPath() + PAGE_URL + "?message=%E5%8A%A0%E5%80%BC%E6%88%90%E5%8A%9F");
    }

    private void withdraw(HttpServletRequest request, HttpServletResponse response, String action, int value,
            int memberNo) throws ServletException, IOException {
        String password = request.getParameter("password");
        
        if (password != null) {
            Member member = web.front_end.member.login.util.LoginConstants.SERVICE.LoadMemberAcctByMemberNo(memberNo);
		    boolean b = web.front_end.member.login.util.LoginConstants.SERVICE.CheckMemberAcctAndPassword(member.getMemberAcct(), SHA256Encode(password));
            if(!b) {
                response.sendRedirect(request.getContextPath() + PAGE_URL + "?message=%E5%B8%B3%E8%99%9F%E5%AF%86%E7%A2%BC%E9%8C%AF%E8%AA%A4");
                return;
            }
            if(SERVICE.getCurrentBalanceByMember(memberNo) < value) {
                response.sendRedirect(request.getContextPath() + PAGE_URL + "?message=%E9%A4%98%E9%A1%8D%E4%B8%8D%E8%B6%B3");
                return;
            }
            WalletTransHist walletTransHist = new WalletTransHist();
            walletTransHist.setMemberNo(memberNo);
            walletTransHist.setWalletDetail("提款" + value + "元");
            walletTransHist.setWalletStatus((byte) 2);
            walletTransHist.setWalletAmount(-value);
            SERVICE.saveOrUpdate(walletTransHist);
            changeWalletAmount(request, response, (double)-value);
        }
        response.sendRedirect(request.getContextPath() + PAGE_URL + "?message=%E6%8F%90%E6%AC%BE%E6%88%90%E5%8A%9F");
    }
    private void changeWalletAmount(HttpServletRequest request, HttpServletResponse response, Double amount) throws UnsupportedEncodingException {
		HttpSession session = request.getSession(); // 取得當前請求的Session
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		ChangeWalletAmountDTO changeWalletAmountDTO = json2Pojo(request, ChangeWalletAmountDTO.class);
		Integer memberNo = ((Integer)session.getAttribute("memberNo"));
		
		WalletMemberService walletMemberService = new WalletMemberServiceImpl();
		
		Double memberCurrentWalletAmount = walletMemberService.loadMemberWalletAmountByMemberNo(memberNo); // 查詢前端欲更改錢包金額的會員的目前錢包金額
		
		// 修改錢包金額至member Table
		boolean modifyWalletAmountIsSuccess = walletMemberService.modifyWalletAmountByMemberNoAndWalletAmount(memberNo, (memberCurrentWalletAmount + amount));
		System.out.println("執行至此2");
//		if(modifyWalletAmountIsSuccess) {
//			changeWalletAmountDTO.setSuccessful(true); // 若處理動作皆成功，傳送true至前端
//			changeWalletAmountDTO.setMessage("交易後的錢包餘額：" + (memberCurrentWalletAmount + changeWalletAmountDTO.getWalletAmount()));
//		}
    }
}
