package web.front_end.member.opa.order.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "opa_so", catalog = "buygo")
public class OpaOrder implements java.io.Serializable {

	private static final long serialVersionUID = 4459406926089661360L;
	private Integer opaSoNo;
	private int memberNo;
	private byte opaSoStatus;
	private Date opaSoDate;
	private double opaProdTotal;
	private double opaDiscount;
	private Integer opaFirAmount;
	private Integer opaSecAmount;
	private Integer opaTotal;
	private Integer opaRealTotal;
	private Byte opaRealStatus;
	private String opaBuyName;
	private String opaBuyTel;
	private String opaBuyAdd;
	private Integer opaFailedReason;
	private Set<OpaOrderdetails> opaOrderdetailses = new HashSet<OpaOrderdetails>(0);

	public OpaOrder() {
	}

	public OpaOrder(int memberNo, byte opaSoStatus, double opaProdTotal, double opaDiscount, String opaBuyName,
			String opaBuyTel, String opaBuyAdd, Integer opaFailedReason) {
		this.memberNo = memberNo;
		this.opaSoStatus = opaSoStatus;
		this.opaProdTotal = opaProdTotal;
		this.opaDiscount = opaDiscount;
		this.opaBuyName = opaBuyName;
		this.opaBuyTel = opaBuyTel;
		this.opaBuyAdd = opaBuyAdd;
		this.opaFailedReason = opaFailedReason;
	}

	public OpaOrder(int memberNo, byte opaSoStatus, Date opaSoDate, double opaProdTotal, double opaDiscount,
			Integer opaFirAmount, Integer opaSecAmount, Integer opaTotal, Integer opaRealTotal, Byte opaRealStatus,
			String opaBuyName, String opaBuyTel, String opaBuyAdd, Set<OpaOrderdetails> opaOrderdetailses) {
		this.memberNo = memberNo;
		this.opaSoStatus = opaSoStatus;
		this.opaSoDate = opaSoDate;
		this.opaProdTotal = opaProdTotal;
		this.opaDiscount = opaDiscount;
		this.opaFirAmount = opaFirAmount;
		this.opaSecAmount = opaSecAmount;
		this.opaTotal = opaTotal;
		this.opaRealTotal = opaRealTotal;
		this.opaRealStatus = opaRealStatus;
		this.opaBuyName = opaBuyName;
		this.opaBuyTel = opaBuyTel;
		this.opaBuyAdd = opaBuyAdd;
		this.opaOrderdetailses = opaOrderdetailses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "OPA_SO_NO", unique = true, nullable = false)
	public Integer getOpaSoNo() {
		return this.opaSoNo;
	}

	public void setOpaSoNo(Integer opaSoNo) {
		this.opaSoNo = opaSoNo;
	}

	@Column(name = "MEMBER_NO", nullable = false)
	public int getMemberNo() {
		return this.memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	@Column(name = "OPA_SO_STATUS", nullable = false)
	public byte getOpaSoStatus() {
		return this.opaSoStatus;
	}

	public void setOpaSoStatus(byte opaSoStatus) {
		this.opaSoStatus = opaSoStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPA_SO_DATE", length = 19)
	public Date getOpaSoDate() {
		return this.opaSoDate;
	}

	public void setOpaSoDate(Date opaSoDate) {
		this.opaSoDate = opaSoDate;
	}

	@Column(name = "OPA_PROD_TOTAL", nullable = false, precision = 22, scale = 0)
	public double getOpaProdTotal() {
		return this.opaProdTotal;
	}

	public void setOpaProdTotal(double opaProdTotal) {
		this.opaProdTotal = opaProdTotal;
	}

	@Column(name = "OPA_DISCOUNT", nullable = false, precision = 22, scale = 0)
	public double getOpaDiscount() {
		return this.opaDiscount;
	}

	public void setOpaDiscount(double opaDiscount) {
		this.opaDiscount = opaDiscount;
	}

	@Column(name = "OPA_FIR_AMOUNT")
	public Integer getOpaFirAmount() {
		return this.opaFirAmount;
	}

	public void setOpaFirAmount(Integer opaFirAmount) {
		this.opaFirAmount = opaFirAmount;
	}

	@Column(name = "OPA_SEC_AMOUNT")
	public Integer getOpaSecAmount() {
		if(this.opaSecAmount == null)
			return 0;
		return this.opaSecAmount;
	}

	public void setOpaSecAmount(Integer opaSecAmount) {
		this.opaSecAmount = opaSecAmount;
	}

	@Column(name = "OPA_TOTAL")
	public Integer getOpaTotal() {
		return this.opaTotal;
	}

	public void setOpaTotal(Integer opaTotal) {
		this.opaTotal = opaTotal;
	}

	@Column(name = "OPA_REAL_TOTAL")
	public Integer getOpaRealTotal() {
		return this.opaRealTotal;
	}

	public void setOpaRealTotal(Integer opaRealTotal) {
		this.opaRealTotal = opaRealTotal;
	}

	@Column(name = "OPA_REAL_STATUS")
	public Byte getOpaRealStatus() {
		return this.opaRealStatus;
	}

	public void setOpaRealStatus(Byte opaRealStatus) {
		this.opaRealStatus = opaRealStatus;
	}

	@Column(name = "OPA_BUY_NAME", nullable = false, length = 20)
	public String getOpaBuyName() {
		return this.opaBuyName;
	}

	public void setOpaBuyName(String opaBuyName) {
		this.opaBuyName = opaBuyName;
	}

	@Column(name = "OPA_BUY_TEL", nullable = false, length = 10)
	public String getOpaBuyTel() {
		return this.opaBuyTel;
	}

	public void setOpaBuyTel(String opaBuyTel) {
		this.opaBuyTel = opaBuyTel;
	}

	@Column(name = "OPA_BUY_ADD", nullable = false, length = 100)
	public String getOpaBuyAdd() {
		return this.opaBuyAdd;
	}

	public void setOpaBuyAdd(String opaBuyAdd) {
		this.opaBuyAdd = opaBuyAdd;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "opaSo")
	public Set<OpaOrderdetails> getOpaOrderdetailses() {
		return this.opaOrderdetailses;
	}

	public void setOpaOrderdetailses(Set<OpaOrderdetails> opaOrderdetailses) {
		this.opaOrderdetailses = opaOrderdetailses;
	}

	@Column(name = "OPA_FAILED_REASON", nullable = true)
	public Integer getOpaFailedReason() {
        return this.opaFailedReason;
    }

	public void setOpaFailedReason(Integer opaFailedReason) {
        this.opaFailedReason = opaFailedReason;
    }
}
