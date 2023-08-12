package web.front_end.member.wallet.service;

import java.util.List;

import org.hibernate.Session;

import core.dao.*;
import core.entity.Core;

public class WalletTransHistService extends BaseService {

    private WalletTransHistDAO walletTransHistDao;

    public WalletTransHistService() {
        walletTransHistDao = new WalletTransHistDAO();
        daos.add(walletTransHistDao);
    }

    public List<WalletTransHist> findAllByMember(int memberNo) {
        List<WalletTransHist> walletTransHists = walletTransHistDao.findAllByMember(memberNo);
        return walletTransHists;
    }

    public int getCurrentBalanceByMember(int memberNo) {
        List<WalletTransHist> walletTransHists = findAllByMember(memberNo);
        int currentBalance = 0;
        for(WalletTransHist walletTransHist : walletTransHists) {
            currentBalance += walletTransHist.getWalletAmount();
        }
        return currentBalance;
    }

    public void saveOrUpdate(WalletTransHist walletTransHist) {
        walletTransHistDao.saveOrUpdate(walletTransHist);
    }
}
