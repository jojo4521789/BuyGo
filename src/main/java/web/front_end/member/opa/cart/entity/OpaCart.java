package web.front_end.member.opa.cart.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import core.entity.Core;
import lombok.Data;
import web.back_end.opa.prod.entity.Prod;

@Data
@Entity
@Table(name = "OPA_CART")
public class OpaCart  extends Core{
	private static final long serialVersionUID = -1954916364705379571L;
	@EmbeddedId
	private OpaCartId opaCartId;
	@Column(name = "OPA_CART_PRODUCTS_QTY")
	private Integer opaCartProductsQty;
	
	@OneToOne
	@JoinColumn(name = "OPA_PROD_NO", insertable = false, updatable = false)
	private Prod prod;
}
