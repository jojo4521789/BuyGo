package web.back_end.lpa.order.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import lombok.*;
import web.back_end.lpa.product.entity.Lpa_Prod;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Lpa_SO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LPA_SO_NO")
	private Integer lpaSoNo;
	@Column(name = "LPA_SO_SEQ", updatable = false)
    private String lpaSoSeq;
	@Column(name = "MEMBER_NO", updatable = false)
    private Integer memberNo;
	@Column(name = "LPA_SO_TIME", insertable = false)
    private Timestamp lpaSoTime;
	@Column(name = "LPA_SO_STATUS")
    private Byte lpaSoStatus;
	@Column(name = "LPA_EVA_SELLER", insertable = false)
    private Byte lpaEvaSeller;
	@Column(name = "LPA_EVA_MEMBER", insertable = false)
    private Byte lpaEvaMember;
	@Column(name = "LPA_TOTAL_AMOUNT")
    private Integer lpaTotalAmount;
	@Column(name = "LPA_DOWN_PAYMENT")
    private Integer lpaDownPayment;
	@Column(name = "LPA_FINAL_PAYMENT")
    private Integer lpaFinalPayment;
	@Column(name = "LPA_REC_NAME")
    private String lpaRecName;
	@Column(name = "LPA_REC_TEL")
    private String lpaRecTel;
	@Column(name = "LPA_SEND_ADDRESS")
    private String lpaSendAddress;
	@ManyToMany
	@JoinTable(
			joinColumns=@JoinColumn(
					referencedColumnName="LPA_SO_NO",
					name="LPA_SO_NO"
					),
			name="lpa_so_details", //關聯表名
			inverseJoinColumns = @JoinColumn(
					name="LPA_PROD_NO",
					referencedColumnName = "LPA_PROD_NO")
			)
	private List<Lpa_Prod> lpa_Prods;
}
