package web.front_end.member.wallet.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import  core.entity.Core; // 導入 Core 類
import lombok.Data;

@Data
@Entity
@Table(name = "WALLET_TRANS_HIST")
public class WalletTransHist extends Core { // 繼承自 Core 類

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

    @Column(name = "OPA_COUPON_NO")
    private Integer opaCouponNo;

    @Column(name = "OPA_COUPON_NAME")
    private String opaCouponName;

    @Column(name = "OPA_DISCOUNT_AMO")
    private Integer opaDiscountAmo;

    @Column(name = "OPA_MIN_AMOUNT")
    private Integer opaMinAmount;

    @Column(name = "OPA_EXP_DATE")
    private Timestamp OPAExpirationDate;

   
}
