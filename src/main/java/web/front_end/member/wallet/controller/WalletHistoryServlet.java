package web.front_end.member.wallet.controller;

import java.io.IOException;
import java.util.List;

import static core.util.CommonUtil.writePojo2Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.util.CommonUtil;
import static web.front_end.member.wallet.util.OpaWalletConstants.SERVICE;
import web.front_end.member.wallet.entity.WalletTransHist;
import web.front_end.member.acc.entity.Member;
import static web.front_end.member.util.SHA256EncoderUtil.SHA256Encode;


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

    private void add(HttpServletRequest request, HttpServletResponse response, String action, int value, int memberNo)
            throws ServletException, IOException {
        String cardNumber = request.getParameter("cardNumber");
        if (cardNumber != null) {
            WalletTransHist walletTransHist = new WalletTransHist();
            walletTransHist.setMemberNo(memberNo);
            walletTransHist.setWalletDetail("儲值" + value + "元");
            walletTransHist.setWalletStatus((byte) 1);
            walletTransHist.setWalletAmount(value);
            SERVICE.saveOrUpdate(walletTransHist);
        }
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
        }
        response.sendRedirect(request.getContextPath() + PAGE_URL + "?message=%E6%8F%90%E6%AC%BE%E6%88%90%E5%8A%9F");
    }
}
