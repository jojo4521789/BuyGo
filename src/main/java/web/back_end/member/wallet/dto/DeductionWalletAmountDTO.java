package web.back_end.member.wallet.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "WALLET_TRANS_HIST")
public class DeductionWalletAmountDTO extends Core{
	private static final long serialVersionUID = 1457755989409740329L;
	
	private Integer memberNo;
	
	private String walletDetail;
	
	private Double deductionAmount; // 欲扣款的金額
}

