package web.front_end.member.pa.cart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class PaCartId implements Serializable{

	private static final long serialVersionUID = -741090021276273471L;

	@Column(name = "MEMBER_NO")
	private int memberNo;
	@Column(name = "PA_PROD_NO")
	private int paProdNo;
	
}
