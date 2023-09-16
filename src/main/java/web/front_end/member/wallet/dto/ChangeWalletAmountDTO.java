package web.front_end.member.wallet.dto;

import core.entity.Core;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ChangeWalletAmountDTO extends Core {
	private static final long serialVersionUID = 1L;

	private Integer memberNo;
	
	private String walletDetail;
	
	private Double walletAmount;
}
