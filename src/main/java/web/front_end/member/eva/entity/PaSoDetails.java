package web.front_end.member.eva.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PA_SO_DETAILS")
public class PaSoDetails extends Core{
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PA_SO_NO")
    private Integer paSoNo;

    @Id
    @Column(name = "PA_PROD_NO")
    private Integer paProdNo;

    @Column(name = "PA_PROD_NAME")
    private String paProdName;

    @Column(name = "PA_PROD_PRICE")
    private Integer paProdPrice;

    @Column(name = "PA_PROD_QTY")
    private Integer paProdQty;
    
//    @ManyToOne
//    @JoinColumn(name = "PA_SO_NO",
//    insertable = false, updatable = false)
//    private PaSo paSo;
    
    @ManyToOne
    @JoinColumn(name = "PA_PROD_NO",
	insertable = false, updatable = false)
    private PaProd paProd;
}