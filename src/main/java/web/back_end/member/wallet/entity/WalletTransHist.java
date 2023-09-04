package web.back_end.member.wallet.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "WALLET_TRANS_HIST")
public class WalletTransHist extends Core{
	private static final long serialVersionUID = 1457755989409740329L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WALLET_NO")
    private Integer walletNo;

    @Column(name = "MEMBER_NO")
    private Integer memberNo;

    @Column(name = "WALLET_DETAIL")
    private String walletDetail;

    @Column(name = "WALLET_STATUS")
    private Integer walletStatus;

    @Column(name = "WALLET_TIME")
    private Timestamp walletTime;

    @Column(name = "WALLET_AMOUNT")
    private Double walletAmount;
}

