package web.front_end.guest.prod.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import web.front_end.member.opa.order.entity.OpaOrderdetails;
import web.front_end.member.opa.cart.entity.OpaCart;

@Entity
@Table(name = "opa_products", catalog = "buygo")
public class OpaProducts implements java.io.Serializable {

	private static final long serialVersionUID = -2322561697925930499L;
	private Integer opaProdNo;
	private OpaPrcats opaPrcats;
	private String opaProdName;
	private int opaProdStockQty;
	private int opaProdShipQty;
	private double opaProdPrice;
	private String opaProdContent;
	private String opaProdUrl;
	private byte opaProdStatus;
	private Date opaProdUpdate;
	private Set<OpaOrderdetails> opaOrderdetailses = new HashSet<OpaOrderdetails>(0);
	private Set<OpaPrpics> opaPrpicses = new HashSet<OpaPrpics>(0);
//	private Set<OpaCart> opaCarts = new HashSet<OpaCart>(0);

	public OpaProducts() {
	}

	public OpaProducts(OpaPrcats opaPrcats, String opaProdName, int opaProdStockQty, int opaProdShipQty,
			double opaProdPrice, String opaProdContent, String opaProdUrl, byte opaProdStatus, Date opaProdUpdate) {
		this.opaPrcats = opaPrcats;
		this.opaProdName = opaProdName;
		this.opaProdStockQty = opaProdStockQty;
		this.opaProdShipQty = opaProdShipQty;
		this.opaProdPrice = opaProdPrice;
		this.opaProdContent = opaProdContent;
		this.opaProdUrl = opaProdUrl;
		this.opaProdStatus = opaProdStatus;
		this.opaProdUpdate = opaProdUpdate;
	}

	public OpaProducts(OpaPrcats opaPrcats, String opaProdName, int opaProdStockQty, int opaProdShipQty,
			double opaProdPrice, String opaProdContent, String opaProdUrl, byte opaProdStatus, Date opaProdUpdate,
			Set<OpaOrderdetails> opaOrderdetailses, Set<OpaPrpics> opaPrpicses, Set<OpaCart> opaCarts) {
		this.opaPrcats = opaPrcats;
		this.opaProdName = opaProdName;
		this.opaProdStockQty = opaProdStockQty;
		this.opaProdShipQty = opaProdShipQty;
		this.opaProdPrice = opaProdPrice;
		this.opaProdContent = opaProdContent;
		this.opaProdUrl = opaProdUrl;
		this.opaProdStatus = opaProdStatus;
		this.opaProdUpdate = opaProdUpdate;
		this.opaOrderdetailses = opaOrderdetailses;
		this.opaPrpicses = opaPrpicses;
//		this.opaCarts = opaCarts;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "OPA_PROD_NO", unique = true, nullable = false)
	public Integer getOpaProdNo() {
		return this.opaProdNo;
	}

	public void setOpaProdNo(Integer opaProdNo) {
		this.opaProdNo = opaProdNo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPA_PRCATS_NO", nullable = false)
	public OpaPrcats getOpaPrcats() {
		return this.opaPrcats;
	}

	public void setOpaPrcats(OpaPrcats opaPrcats) {
		this.opaPrcats = opaPrcats;
	}

	@Column(name = "OPA_PROD_NAME", nullable = false, length = 40)
	public String getOpaProdName() {
		return this.opaProdName;
	}

	public void setOpaProdName(String opaProdName) {
		this.opaProdName = opaProdName;
	}

	@Column(name = "OPA_PROD_STOCK_QTY", nullable = false)
	public int getOpaProdStockQty() {
		return this.opaProdStockQty;
	}

	public void setOpaProdStockQty(int opaProdStockQty) {
		this.opaProdStockQty = opaProdStockQty;
	}

	@Column(name = "OPA_PROD_SHIP_QTY", nullable = false)
	public int getOpaProdShipQty() {
		return this.opaProdShipQty;
	}

	public void setOpaProdShipQty(int opaProdShipQty) {
		this.opaProdShipQty = opaProdShipQty;
	}

	@Column(name = "OPA_PROD_PRICE", nullable = false, precision = 22, scale = 0)
	public double getOpaProdPrice() {
		return this.opaProdPrice;
	}

	public void setOpaProdPrice(double opaProdPrice) {
		this.opaProdPrice = opaProdPrice;
	}

	@Column(name = "OPA_PROD_CONTENT", nullable = false, length = 65535)
	public String getOpaProdContent() {
		return this.opaProdContent;
	}

	public void setOpaProdContent(String opaProdContent) {
		this.opaProdContent = opaProdContent;
	}

	@Column(name = "OPA_PROD_URL", nullable = false, length = 65535)
	public String getOpaProdUrl() {
		return this.opaProdUrl;
	}

	public void setOpaProdUrl(String opaProdUrl) {
		this.opaProdUrl = opaProdUrl;
	}

	@Column(name = "OPA_PROD_STATUS", nullable = false)
	public byte getOpaProdStatus() {
		return this.opaProdStatus;
	}

	public void setOpaProdStatus(byte opaProdStatus) {
		this.opaProdStatus = opaProdStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPA_PROD_UPDATE", nullable = false, length = 19)
	public Date getOpaProdUpdate() {
		return this.opaProdUpdate;
	}

	public void setOpaProdUpdate(Date opaProdUpdate) {
		this.opaProdUpdate = opaProdUpdate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "opaProducts")
	public Set<OpaOrderdetails> getOpaOrderdetailses() {
		return this.opaOrderdetailses;
	}

	public void setOpaOrderdetailses(Set<OpaOrderdetails> opaOrderdetailses) {
		this.opaOrderdetailses = opaOrderdetailses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "opaProducts")
	public Set<OpaPrpics> getOpaPrpicses() {
		return this.opaPrpicses;
	}

	public void setOpaPrpicses(Set<OpaPrpics> opaPrpicses) {
		this.opaPrpicses = opaPrpicses;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "opaProducts")
//	public Set<OpaCart> getOpaCarts() {
//		return this.opaCarts;
//	}
//
//	public void setOpaCarts(Set<OpaCart> opaCarts) {
//		this.opaCarts = opaCarts;
//	}

	public static String getPreviewURL(OpaProducts opaProduct) {
		Set<OpaPrpics> opaPrpicses = opaProduct.getOpaPrpicses();
		if(opaPrpicses.size() > 0) {
			OpaPrpics opaPrpics = opaPrpicses.iterator().next();
			return "products/preview/" + opaPrpics.getOpaPrpicsNo();
		}
		return null;
	}
}
