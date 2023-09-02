package web.back_end.member.wallet.service;

import java.util.List;

import web.back_end.member.wallet.entity.WalletTransHist;
import web.front_end.member.acc.entity.Member;

public interface WalletMemberService {
	List<Member> loadMemberList(Member member);
	Boolean modifyWalletAmountByMemberNoAndWalletAmount(Integer memberNo, Double memberWalletAmount);
	Boolean addWalletTransHist(WalletTransHist walletTransHist);
	Double loadMemberWalletAmountByMemberNo(Integer memberNo);
}
