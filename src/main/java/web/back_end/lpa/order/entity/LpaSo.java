package web.back_end.lpa.order.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import core.entity.Core;
import lombok.*;
import web.back_end.lpa.product.entity.LpaProd;

@Entity
@Table(name = "lpa_so")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LpaSo extends Core{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LPA_SO_NO")
	private Integer lpaSoNo;
	@Column(name = "LPA_SO_SEQ", insertable = false)
    private String lpaSoSeq;
	@Column(name = "MEMBER_NO", updatable = false)
    private Integer memberNo;
	@Column(name = "LPA_SO_TIME", insertable = false) // DB自動新增時間
    private Timestamp lpaSoTime;
	@Column(name = "LPA_SO_STATUS", insertable = false) // 預設狀態0
    private Byte lpaSoStatus;
	@Column(name = "LPA_EVA_SELLER", insertable = false)
    private Byte lpaEvaSeller;
	@Column(name = "LPA_EVA_MEMBER", insertable = false)
    private Byte lpaEvaMember;
	@Column(name = "LPA_TOTAL_AMOUNT")
    private Integer lpaTotalAmount;
	@Column(name = "LPA_DOWN_PAYMENT", insertable = false)
    private Integer lpaDownPayment;
	@Column(name = "LPA_DELIVER_METHOD")
    private String lpaDeliverMethod;
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
	private List<LpaProd> lpaProds;
	
	@OneToMany
	@JoinColumn(name = "LPA_SO_NO", referencedColumnName = "LPA_SO_NO")
	private List<LpaSoDetails> lpaSoDetails;
	
	
}
