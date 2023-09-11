package web.front_end.member.pa.req.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.front_end.member.acc.entity.Member;

@Entity
@Getter
@Setter
@Table(name ="PA_REQUEST")
@NoArgsConstructor
@AllArgsConstructor
public class MPaReq extends Core{

	private static final long serialVersionUID = -4506469193851408883L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="PA_RQ_NO", insertable = false)
    private Integer paRqNo;
	@Column(name ="MEMBER_NO_MEMBER")
    private Integer memberNoMember;
	@Column(name ="MEMBER_NO_SELLER", insertable = false, updatable = false)
    private Integer memberNoSeller;
	@Column(name ="PA_RQ_PROD_NAME")
    private String paRqProdName;
	@Column(name ="PA_RQ_URL")
    private String paRqUrl;
	@Column(name ="PA_RQ_PRICE")
    private Integer paRqPrice;
	@Column(name ="PA_RQ_QTY")
    private Integer paRqQty;
	@Column(name ="PA_RQ_NOTE")
    private String paRqNote;
	@Column(name ="PA_RQ_STAT")
    private Integer paRqStat;
	@Column(name ="PA_RQ_STARTDATE")
	private Timestamp paRqStartDate;
	
	 @ManyToOne
	    @JoinColumn(name = "MEMBER_NO_SELLER", referencedColumnName = "MEMBER_NO")
	    private Member member;

	}

	

