package web.front_end.member.wallet.dao;

import java.util.List;
import core.dao.CoreDao; 
import web.front_end.member.wallet.entity.WalletTransHist;

public interface WalletTransHistDao extends CoreDao<WalletTransHist, Integer> {
    WalletTransHist getById(Integer id);

    List<WalletTransHist> getAll();

    void saveWalletTransHist(WalletTransHist walletTransHist);

    void updateWalletTransHist(WalletTransHist walletTransHist);

    void deleteWalletTransHist(WalletTransHist walletTransHist);
    

     
}
