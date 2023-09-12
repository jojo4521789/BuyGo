package web.front_end.member.wallet.controller;
import static web.front_end.member.wallet.util.OpaWalletConstants.SERVICE;
import static web.front_end.member.opa.order.util.OpaOrderConstants.SERVICE;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import static core.util.CommonUtil.writePojo2Json;

import core.util.CommonUtil;
import web.front_end.member.opa.order.service.OpaOrderService;
import web.front_end.member.wallet.entity.Wallet;
import web.front_end.member.wallet.service.WalletTransHistService;
import javax.servlet.annotation.*;

@WebServlet("/needLoginApi/member/wallet/balance")
public class WalletServlet extends HttpServlet {
    private static final long serialVersionUID = 1640403658892597808L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int memberNo = (Integer)session.getAttribute("memberNo");
        int currentBalance = web.front_end.member.wallet.util.OpaWalletConstants.SERVICE.getCurrentBalanceByMember(memberNo);
        int pendingTransaction = web.front_end.member.opa.order.util.OpaOrderConstants.SERVICE.getPendingTransactionByMember(memberNo);
        int totalBalance = currentBalance - pendingTransaction;
        Wallet wallet = new Wallet(currentBalance, pendingTransaction, totalBalance);
        wallet.setSuccessful(true);
       	response.setCharacterEncoding("UTF-8");
        writePojo2Json(response, wallet);
    }
}
