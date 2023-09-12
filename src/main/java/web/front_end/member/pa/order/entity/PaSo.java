package web.front_end.member.pa.order.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import core.entity.Core;
import lombok.*;
import web.front_end.member.acc.entity.Member;
import web.front_end.member.pa.prod.entity.PaProd;

@Entity
@Table(name = "pa_so")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaSo extends Core{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PA_SO_NO")
	private Integer paSoNo;
	@Column(name = "PA_SO_SEQ", insertable = false)
    private String paSoSeq;
	@Column(name = "MEMBER_NO", updatable = false)
    private Integer memberNo;
	@Column(name = "PA_SO_TIME", insertable = false) // DB自動新增時間
    private Timestamp paSoTime;
	@Column(name = "PA_SO_STATUS", insertable = false) // 預設狀態0
    private Byte paSoStatus;
	@Column(name = "PA_EVA_SELLER", insertable = false)
    private Byte paEvaSeller;
	@Column(name = "PA_EVA_MEMBER", insertable = false)
    private Byte paEvaMember;
	@Column(name = "PA_TOTAL_AMOUNT")
    private Integer paTotalAmount;
	@Column(name = "PA_DELIVER_METHOD")
    private String paDeliverMethod;
	@Column(name = "PA_DELIVER_TIME", insertable = false)
    private Timestamp paDeliverTime;
	@Column(name = "PA_DELIVER_MSG", insertable = false)
    private String paDeliverMsg;
	@Column(name = "PA_REC_NAME")
    private String paRecName;
	@Column(name = "PA_REC_TEL")
    private String paRecTel;
	@Column(name = "PA_SEND_ADDRESS")
    private String paSendAddress;
	@ManyToMany
	@JoinTable(
			joinColumns=@JoinColumn(
					referencedColumnName="PA_SO_NO",
					name="PA_SO_NO"
					),
			name="pa_so_details", //關聯表名
			inverseJoinColumns = @JoinColumn(
					name="PA_PROD_NO",
					referencedColumnName = "PA_PROD_NO")
			)
	private List<PaProd> paProds;
	
	@OneToMany
	@JoinColumn(name = "PA_SO_NO", referencedColumnName = "PA_SO_NO")
	private List<PaSoDetails> paSoDetails;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO",
	insertable = false, updatable = false)
	private Member member;
	
	@OneToMany
	@JoinColumn(name = "PA_SO_NO", referencedColumnName = "PA_SO_NO")
	private List<PaReturn> paReturn;
	
}
