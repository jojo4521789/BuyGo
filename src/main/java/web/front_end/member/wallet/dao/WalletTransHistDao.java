package web.front_end.member.wallet.dao;

import java.util.List;

import core.dao.CoreDao; 
import web.front_end.member.wallet.entity.WalletTransHist;

public interface WalletTransHistDao extends CoreDao<WalletTransHist, Integer> {
	public List<WalletTransHist> selectAllByMember(int memberNo);
	public void saveOrUpdate(WalletTransHist entity);
}
