package web.front_end.member.eva.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.front_end.member.acc.entity.Member;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PA_SO")
public class PaSo extends Core{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PA_SO_NO")
    private Integer paSoNo;

    @Column(name = "PA_SO_SEQ")
    private String paSoSeq;
    
    @Column(name = "MEMBER_NO")
    private Integer memberNo;

    @Column(name = "PA_SO_TIME")
    private Timestamp paSoTime;

    @Column(name = "PA_SO_STATUS")
    private Integer paSoStatus;
    @Column(name = "PA_EVA_SELLER")
    private Integer paEvaSeller;
    
    @Column(name = "PA_EVA_MEMBER")
    private Integer paEvaMember;
    
    @Column(name = "PA_TOTAL_AMOUNT")
    private Integer paTotalAmount;
    
    @Column(name = "PA_DELIVER_METHOD")
    private String paDeliverMethod;
    
    @Column(name = "PA_DELIVER_TIME")
    private Timestamp paDeliverTime;
    
    @Column(name = "PA_DELIVER_MSG")
    private String paDeliverMsg;
    
    @Column(name = "PA_REC_NAME")
    private String paRecName;
    
    @Column(name = "PA_REC_TEL")
    private String paRecTel;
    
    @Column(name = "PA_SEND_ADDRESS")
    private String paSendAddress;
    
    @ManyToOne
    @JoinColumn(name = "MEMBER_NO",
    insertable = false, updatable = false)
    private Member member;
    
    @OneToMany
    @JoinColumn(name = "PA_SO_NO",
    referencedColumnName = "PA_SO_NO")
    private List<PaSoDetails> paSoDetails;
}