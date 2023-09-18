package web.front_end.member.wallet.service.impl;

import java.util.List;

import web.front_end.member.wallet.dao.WalletTransHistDao;
import web.front_end.member.wallet.dao.impl.WalletTransHistDaoImpl;
import web.front_end.member.wallet.entity.WalletTransHist;
import web.front_end.member.wallet.service.WalletTransHistService;

public class WalletTransHistServiceImpl implements WalletTransHistService {

    private WalletTransHistDao walletTransHistDao;

    public WalletTransHistServiceImpl() {
        walletTransHistDao = new WalletTransHistDaoImpl();
    }
    
    @Override
    public List<WalletTransHist> findAllByMember(int memberNo) {
        List<WalletTransHist> walletTransHists = walletTransHistDao.selectAllByMember(memberNo);
        return walletTransHists;
    }
    
    @Override
    public int getCurrentBalanceByMember(int memberNo) {
        List<WalletTransHist> walletTransHists = findAllByMember(memberNo);
        int currentBalance = 0;
        for(WalletTransHist walletTransHist : walletTransHists) {
            currentBalance += walletTransHist.getWalletAmount();
        }
        return currentBalance;
    }
    
    @Override
    public void saveOrUpdate(WalletTransHist walletTransHist) {
        walletTransHistDao.saveOrUpdate(walletTransHist);
    }
}
