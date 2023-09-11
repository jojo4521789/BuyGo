package web.front_end.member.wallet.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "wallet_trans_hist", catalog = "buygo")
public class WalletTransHist implements java.io.Serializable {

	private static final long serialVersionUID = 6326180603777179457L;
	private Integer walletNo;
	private int memberNo;
	private String walletDetail;
	private byte walletStatus;
	private Date walletTime;
	private double walletAmount;

	public WalletTransHist() {
	}

	public WalletTransHist(int memberNo, String walletDetail, byte walletStatus, double walletAmount) {
		this.memberNo = memberNo;
		this.walletDetail = walletDetail;
		this.walletStatus = walletStatus;
		this.walletAmount = walletAmount;
	}

	public WalletTransHist(int memberNo, String walletDetail, byte walletStatus, Date walletTime, double walletAmount) {
		this.memberNo = memberNo;
		this.walletDetail = walletDetail;
		this.walletStatus = walletStatus;
		this.walletTime = walletTime;
		this.walletAmount = walletAmount;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "WALLET_NO", unique = true, nullable = false)
	public Integer getWalletNo() {
		return this.walletNo;
	}

	public void setWalletNo(Integer walletNo) {
		this.walletNo = walletNo;
	}

	@Column(name = "MEMBER_NO", nullable = false)
	public int getMemberNo() {
		return this.memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	@Column(name = "WALLET_DETAIL", nullable = false, length = 40)
	public String getWalletDetail() {
		return this.walletDetail;
	}

	public void setWalletDetail(String walletDetail) {
		this.walletDetail = walletDetail;
	}

	@Column(name = "WALLET_STATUS", nullable = false)
	public byte getWalletStatus() {
		return this.walletStatus;
	}

	public void setWalletStatus(byte walletStatus) {
		this.walletStatus = walletStatus;
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "WALLET_TIME", length = 19, updatable = false, insertable = false)
	public Date getWalletTime() {
		return this.walletTime;
	}

	public void setWalletTime(Date walletTime) {
		this.walletTime = walletTime;
	}

	@Column(name = "WALLET_AMOUNT", nullable = false, precision = 22, scale = 0)
	public double getWalletAmount() {
		return this.walletAmount;
	}

	public void setWalletAmount(double walletAmount) {
		this.walletAmount = walletAmount;
	}

}
