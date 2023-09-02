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

    @Column(name = "MEMBER_NO")
    private Integer memberNo;

    @Column(name = "PA_SO_TIME")
    private Timestamp paSoTime;

    @Column(name = "PA_SO_STATUS")
    private Integer paSoStatus;

    @Column(name = "PA_SO_1ST")
    private Integer paSo1st;

    @Column(name = "PA_SO_2ND")
    private Integer paSo2nd;

    @Column(name = "PA_SO_TOTAL")
    private Integer paSoTotal;

    @Column(name = "PA_REAL_STATUS")
    private Integer paRealStatus;

    @Column(name = "PA_BUY_NAME")
    private String paBuyName;

    @Column(name = "PA_BUY_TEL")
    private String paBuyTel;

    @Column(name = "PA_BUY_ADD")
    private String paBuyAdd;

    @Column(name = "PA_EVA_SELLER")
    private Integer paEvaSeller;

    @Column(name = "PA_EVA_MEMBER")
    private Integer paEvaMember;
    
    @ManyToOne
    @JoinColumn(name = "MEMBER_NO",
    insertable = false, updatable = false)
    private Member member;
    
    @OneToMany
    @JoinColumn(name = "PA_SO_NO",
    referencedColumnName = "PA_SO_NO")
    private List<PaSoDetails> paSoDetails;
}