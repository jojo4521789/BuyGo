package web.front_end.guest.prod.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "opa_prpics", catalog = "buygo")
public class OpaPrpics implements java.io.Serializable {

	private static final long serialVersionUID = -3920504291846309433L;
	private Integer opaPrpicsNo;
	private OpaProducts opaProducts;
	private byte[] opaProdPicture;

	public OpaPrpics() {
	}

	public OpaPrpics(OpaProducts opaProducts) {
		this.opaProducts = opaProducts;
	}

	public OpaPrpics(OpaProducts opaProducts, byte[] opaProdPicture) {
		this.opaProducts = opaProducts;
		this.opaProdPicture = opaProdPicture;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "OPA_PRPICS_NO", unique = true, nullable = false)
	public Integer getOpaPrpicsNo() {
		return this.opaPrpicsNo;
	}

	public void setOpaPrpicsNo(Integer opaPrpicsNo) {
		this.opaPrpicsNo = opaPrpicsNo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPA_PROD_NO", nullable = false)
	public OpaProducts getOpaProducts() {
		return this.opaProducts;
	}

	public void setOpaProducts(OpaProducts opaProducts) {
		this.opaProducts = opaProducts;
	}

	@Column(name = "OPA_PROD_PICTURE")
	public byte[] getOpaProdPicture() {
		return this.opaProdPicture;
	}

	public void setOpaProdPicture(byte[] opaProdPicture) {
		this.opaProdPicture = opaProdPicture;
	}
}
