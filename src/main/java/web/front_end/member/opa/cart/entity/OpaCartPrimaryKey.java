package web.front_end.member.opa.cart.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class OpaCartPrimaryKey implements Serializable {
   
	private static final long serialVersionUID = 449985572679164642L;
	private Integer memberNo;
    private Integer opaProdNo;
}