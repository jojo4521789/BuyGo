package web.front_end.member.opa.order.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import java.sql.Timestamp;
import java.util.List;
import lombok.Data;
import web.back_end.opa.req.entity.OpaRequest;
import web.front_end.member.opa.cart.entity.OpaCart;

@Data
@Entity
@Table(name = "OPA_SO")
public class OpaOrder {

    @Id
    @Column(name = "OPA_SO_NO")
    private Integer soNo;

    @Column(name = "OPA_PROD_TOTAL")
    private Double prodTotal;

    @Column(name = "OPA_BUY_NAME")
    private String buyName;

    @Column(name = "OPA_SO_STATUS")
    private Integer orderStatus;

    @Column(name = "OPA_SO_DATE")
    private Timestamp orderDate;

    @Column(name = "OPA_DISCOUNT")
    private Double discountAmount;

    @Column(name = "OPA_FIR_AMOUNT")
    private Integer firstPaymentAmount;

    @Column(name = "OPA_SEC_AMOUNT")
    private Integer secondPaymentAmount;

    @Column(name = "OPA_TOTAL")
    private Integer totalAmount;

    @Column(name = "OPA_REAL_TOTAL")
    private Integer realTotalAmount;

    @Column(name = "OPA_REAL_STATUS")
    private Integer realPaymentStatus;

    @Column(name = "OPA_BUY_TEL")
    private String buyerTelephone;

    @Column(name = "OPA_BUY_ADD")
    private String buyerAddress;

    // One-to-Many 映射到 OPA_ORDERDETAILS 表
    @OneToMany(mappedBy = "platformOrder", cascade = CascadeType.ALL)
    private List<OpaOrderDetails> opaOrderDetails; 

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    // One-to-One 映射到 OPA_REQUEST 表
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private OpaRequest opaRequest;

    // One-to-Many 映射到 OPA_CART 表
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OpaCart> opaCarts;

    
}
