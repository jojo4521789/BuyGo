package web.front_end.member.pa.cart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.entity.Core;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "PA_CART", catalog = "buygo")
public class PaCart extends Core{

	private static final long serialVersionUID = 5443652730373363992L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_NO")
	private Integer memberNo;
	@Column(name = "PA_PROD_NO")
	private Integer paProdNo;
	@Column(name = "PA_PROD_SUM")
	private Integer paProdSum;
	@Column(name = "PA_ORD_QTY")
	private Integer paOrdQty;

}
