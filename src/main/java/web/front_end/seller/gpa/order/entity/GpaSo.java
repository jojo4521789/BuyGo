package web.front_end.seller.gpa.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "GPA_SO")
public class GpaSo extends Core{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GPA_SO_NO")
    private Integer gpaSoNo;

    @Column(name = "GPA_PROD_NO")
    private Integer gpaProdNo;

	@Column(name = "MEMBER_NO")
    private Integer memberNo;

    @Column(name = "GPA_PROD_COUNT")
    private Integer gpaProdCount;

    @Column(name = "GPA_PROD_PRICE")
    private Integer gpaProdPrice;

    @Column(name = "GPA_PROD_TOTAL")
    private Integer gpaProdTotal;

    @Column(name = "GPA_SO_STAT")
    private Integer gpaSoStat;

    @Column(name = "GPA_BUY_NAME")
    private String gpaBuyName;

    @Column(name = "GPA_BUY_TEL")
    private String gpaBuyTel;

    @Column(name = "GPA_BUY_ADD")
    private String gpaBuyAdd;

    @Column(name = "GPA_EVA_SELLER")
    private Integer gpaEvaSeller;

    @Column(name = "GPA_EVA_MEMBER")
    private Integer gpaEvaMember;
}
