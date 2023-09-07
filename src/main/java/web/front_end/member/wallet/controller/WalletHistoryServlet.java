package web.front_end.member.wallet.controller;

import java.io.IOException;
import java.util.List;

import static core.util.CommonUtil.writePojo2Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;
import static web.front_end.member.wallet.util.OpaWalletConstants.SERVICE;
import web.front_end.member.wallet.entity.WalletTransHist;


@WebServlet("/needLoginApi/member/wallet/history")
public class WalletHistoryServlet extends HttpServlet {
    static final String PAGE_URL = "/front_end/pages/member/wallet.html";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int memberNo = 1;
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
        int memberNo = 1;
        int value;
        try {
            value = Integer.parseInt(request.getParameter("value"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + PAGE_URL);
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
        response.sendRedirect(request.getContextPath() + PAGE_URL);
    }

    private void withdraw(HttpServletRequest request, HttpServletResponse response, String action, int value,
            int memberNo) throws ServletException, IOException {
        String password = request.getParameter("password");
        if (password != null) {
            WalletTransHist walletTransHist = new WalletTransHist();
            walletTransHist.setMemberNo(memberNo);
            walletTransHist.setWalletDetail("提款" + value + "元");
            walletTransHist.setWalletStatus((byte) 2);
            walletTransHist.setWalletAmount(-value);
            SERVICE.saveOrUpdate(walletTransHist);
        }
        response.sendRedirect(request.getContextPath() + PAGE_URL);
    }
}
