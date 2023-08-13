package web.front_end.member.opa.order.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(OpaOrderDetailsPrimaryKey.class)
public class OpaOrderDetails implements Serializable {

    @Id
    @Column(name = "OPA_SO_NO")
    private Integer opaSoNo;

    @Id
    @Column(name = "OPA_PROD_NO")
    private Integer opaProdNo;

    @ManyToOne
    @JoinColumn(name = "OPA_SO_NO", insertable = false, updatable = false)
    private OpaOrder opaOrder;

    @ManyToOne
    @JoinColumn(name = "OPA_PROD_NO", insertable = false, updatable = false)
    private OpaProduct opaProduct;

    @Column(name = "OPA_PROD_NAME")
    private String opaProdName;

    @Column(name = "OPA_PROD_PRICE")
    private Double opaProdPrice;

    @Column(name = "OPA_ORD_QTY")
    private Integer opaOrdQty;

}