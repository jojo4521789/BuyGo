package web.front_end.member.pa.cart.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import core.entity.Core;
import lombok.Data;
import web.front_end.prodlist.entity.PaProdlist;

@Data
@Entity
@Table(name = "PA_CART")
public class PaCart extends Core{

	private static final long serialVersionUID = 5443652730373363992L;

	@EmbeddedId
	private PaCartId paCartId;
	
	@Column(name = "PA_PROD_SUM")
	private Integer paProdSum;
	@Column(name = "PA_ORD_QTY")
	private Integer paOrdQty;
	
	//關連到商品表格(可能需要換成祥暘的entity
	@OneToOne
	@JoinColumn(name = "PA_PROD_NO", insertable = false, updatable = false)
	private PaProdlist paProdlist;


	}
	
	


