package web.back_end.member.wallet.service.impl;

import java.util.List;

import web.back_end.member.wallet.dao.WalletMemberDao;
import web.back_end.member.wallet.dao.WalletTransHistDao;
import web.back_end.member.wallet.dao.impl.WalletMemberDaoImpl;
import web.back_end.member.wallet.dao.impl.WalletTransHistDaoImpl;
import web.back_end.member.wallet.entity.WalletTransHist;
import web.back_end.member.wallet.service.WalletMemberService;
import web.front_end.member.acc.entity.Member;

public class WalletMemberServiceImpl implements WalletMemberService {
	private WalletMemberDao walletMemberDao;
	private WalletTransHistDao walletTransHistDao;

	public WalletMemberServiceImpl() {
		walletMemberDao = new WalletMemberDaoImpl();
		walletTransHistDao = new WalletTransHistDaoImpl();
	}

	@Override
	public List<Member> loadMemberList(Member member) {
		return walletMemberDao.select(member);
	}

	@Override
	public Boolean modifyWalletAmountByMemberNoAndWalletAmount(Integer memberNo, Double memberWalletAmount) {
		int result = walletMemberDao.updateWalletAmountByMemberNoAndWalletAmount(memberNo, memberWalletAmount);
		if(result == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Boolean addWalletTransHist(WalletTransHist walletTransHist) {
		int result = walletTransHistDao.insert(walletTransHist);
		if(result == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Double loadMemberWalletAmountByMemberNo(Integer memberNo) {
		Member member = walletMemberDao.selectMemberByMemberNo(memberNo);
		return member.getMemberWalletAmount();
	}
}
