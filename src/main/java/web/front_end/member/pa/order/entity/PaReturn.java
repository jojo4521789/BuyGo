package web.front_end.member.pa.order.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "pa_return")
@Data
public class PaReturn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PA_RTN_NO")
	private Integer paRtnNo;
	@Column(name = "PA_RTN_SEQ")
	private String paRtnSeq;
	@Column(name = "PA_SO_NO")
    private Integer paSoNo;
	@Column(name = "PA_RTN_DATE", insertable = false) // DB自動新增時間
    private Timestamp paRtnDate;
	@Column(name = "PA_RTN_AMOUNT") // 退款金額
    private Integer paRtnAmount;
	@Column(name = "PA_RTN_RSN")
    private String paRtnReason;
	@Column(name = "PA_RTN_TEXT")
    private String paRtnText;
	@Column(name = "PA_RTN_STAT", insertable = false) // 狀態預設0
    private Integer paRtnStat;
	
	@OneToMany
	@JoinColumn(name = "PA_RTN_NO", referencedColumnName = "PA_RTN_NO")
	private List<PaReturnDetails> paReturnDetails;
//	@OneToOne
//	@JoinColumn(name = "PA_SO_NO",
//	insertable = false, updatable = false)
//	private PaSo paSo;
}
