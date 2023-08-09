package web.back_end.opa.order.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(OpaOrderDetailsPrimaryKey.class)
public class OpaOrderDetails implements Serializable {

    
	private static final long serialVersionUID = 1481836350472379646L;

	@Id
    @Column(name = "OPA_SO_NO")
    private Integer opaSoNo;

    @Id
    @Column(name = "OPA_PROD_NO")
    private Integer opaProdNo;

    @ManyToOne
    @JoinColumn(name = "OPA_SO_NO", insertable = false, updatable = false)
    private OpaOrderBack opaOrderBack;

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
