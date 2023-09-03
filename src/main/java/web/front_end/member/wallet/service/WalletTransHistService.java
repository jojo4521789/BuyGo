package web.front_end.member.wallet.service;

import java.util.List;

import core.service.CoreService;
import web.front_end.member.wallet.entity.WalletTransHist;

public interface WalletTransHistService extends CoreService {
    public List<WalletTransHist> findAllByMember(int memberNo);

    public int getCurrentBalanceByMember(int memberNo);

    public void saveOrUpdate(WalletTransHist walletTransHist);
}
