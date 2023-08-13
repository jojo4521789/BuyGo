package web.front_end.member.lpa.cart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.entity.Core;
import lombok.Data;


@Data
@Entity
@Table(name = "LPA_CART")
public class Cart extends Core {
	private static final long serialVersionUID = 1329886329266797536L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_NO")
    private Integer memberNo;
    
    @Column(name = "LPA_PROD_NO")
    private Integer lpaProdNo;
    
    @Column(name = "LPA_PROD_PRICE")
    private Integer lpaProdPrice;
    
    @Column(name = "LPA_CART_QTY")
    private Integer lpaCartQty;
}