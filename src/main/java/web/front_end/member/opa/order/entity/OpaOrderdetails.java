package web.front_end.member.opa.order.entity;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import core.entity.Core;
import web.front_end.guest.prod.entity.OpaProducts;


@Entity
@Table(name = "opa_orderdetails", catalog = "buygo")
public class OpaOrderdetails extends Core implements java.io.Serializable {

	private static final long serialVersionUID = 5461408637317149354L;
	private OpaOrderdetailsId id;
	private OpaProducts opaProducts;
	private OpaOrder opaSo;
	private String opaProdName;
	private double opaProdPrice;
	private int opaOrdQty;

	public OpaOrderdetails() {
	}

	public OpaOrderdetails(OpaOrderdetailsId id, OpaProducts opaProducts, OpaOrder opaSo, String opaProdName,
			double opaProdPrice, int opaOrdQty) {
		this.id = id;
		this.opaProducts = opaProducts;
		this.opaSo = opaSo;
		this.opaProdName = opaProdName;
		this.opaProdPrice = opaProdPrice;
		this.opaOrdQty = opaOrdQty;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "opaSoNo", column = @Column(name = "OPA_SO_NO", nullable = false)),
			@AttributeOverride(name = "opaProdNo", column = @Column(name = "OPA_PROD_NO", nullable = false)) })
	public OpaOrderdetailsId getId() {
		return this.id;
	}

	public void setId(OpaOrderdetailsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPA_PROD_NO", nullable = false, insertable = false, updatable = false)
	public OpaProducts getOpaProducts() {
		return this.opaProducts;
	}

	public void setOpaProducts(OpaProducts opaProducts) {
		this.opaProducts = opaProducts;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPA_SO_NO", nullable = false, insertable = false, updatable = false)
	public OpaOrder getOpaSo() {
		return this.opaSo;
	}

	public void setOpaSo(OpaOrder opaSo) {
		this.opaSo = opaSo;
	}

	@Column(name = "OPA_PROD_NAME", nullable = false, length = 40)
	public String getOpaProdName() {
		return this.opaProdName;
	}

	public void setOpaProdName(String opaProdName) {
		this.opaProdName = opaProdName;
	}

	@Column(name = "OPA_PROD_PRICE", nullable = false, precision = 22, scale = 0)
	public double getOpaProdPrice() {
		return this.opaProdPrice;
	}

	public void setOpaProdPrice(double opaProdPrice) {
		this.opaProdPrice = opaProdPrice;
	}

	@Column(name = "OPA_ORD_QTY", nullable = false)
	public int getOpaOrdQty() {
		return this.opaOrdQty;
	}

	public void setOpaOrdQty(int opaOrdQty) {
		this.opaOrdQty = opaOrdQty;
	}

}
