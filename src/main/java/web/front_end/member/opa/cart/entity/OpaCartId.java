package web.front_end.member.opa.cart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class OpaCartId implements Serializable{
	private static final long serialVersionUID = 3849249718367862633L;
	
	@Column(name = "MEMBER_NO")
	private int memberNo;
	@Column(name = "OPA_PROD_NO")
	private int opaProdNo;
	
}
