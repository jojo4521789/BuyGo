package web.front_end.member.lpa.cart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "LPA_CART")
public class LpaCart extends Core {
	private static final long serialVersionUID = 1329886329266797536L;
	@Id
	@Column(name = "MEMBER_NO")
	private Integer memberNo;
	@Id
	@Column(name = "LPA_PROD_NO")
	private Integer lpaProdNo;

	@Column(name = "LPA_PROD_PRICE")
	private Integer lpaProdPrice;

	@Column(name = "LPA_CART_QTY")
	private Integer lpaCartQty;

}
