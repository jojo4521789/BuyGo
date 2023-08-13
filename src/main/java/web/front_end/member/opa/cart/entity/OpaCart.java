package web.front_end.member.opa.cart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@IdClass(OpaCartPrimaryKey.class)
@Table(name = "OPA_CART")
public class OpaCart {

    @Id
    @Column(name = "MEMBER_NO")
    private Integer memberNo;

    @Id
    @Column(name = "OPA_PROD_NO")
    private Integer opaProdNo;

    @Column(name = "OPA_PROD_PRICE") 
    private Integer opaProdPrice;

    @Column(name = "OPA_CART_PRODUCTS_QTY")
    private Integer opaCartProductsQty;

    
}
