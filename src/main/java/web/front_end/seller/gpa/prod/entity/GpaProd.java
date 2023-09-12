package web.front_end.seller.gpa.prod.entity;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import web.front_end.seller.gpa.order.entity.GpaSo;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GPA_PROD")
public class GpaProd extends Core{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GPA_PROD_NO")
    private Integer gpaProdNo;

    @Column(name = "MEMBER_NO")
    private Integer memberNo;

    @Column(name = "GPA_CATS_NO")
    private Integer gpaCatsNo;

    @Column(name = "GPA_PROD_NAME")
    private String gpaProdName;

    @Column(name = "GPA_FIRST_PRICE")
    private Integer gpaFirstPrice;

    @Column(name = "GPA_MINI_COUNT")
    private Integer gpaMiniCount;

    @Column(name = "GPA_MAX_COUNT")
    private Integer gpaMaxCount;

    @Column(name = "GPA_PRE_PROD")
    private Integer gpaPreProd;

    @Column(name = "GPA_PROD_CONTENT")
    private String gpaProdContent;

    @Column(name = "GPA_END_DATE")
    private Timestamp gpaEndDate;
    
    @ManyToOne
    @JoinColumn(name = "MEMBER_NO",
    insertable = false, updatable = false)
    private Member member;
    
    @OneToMany
    @JoinColumn(name = "GPA_PROD_NO",
    referencedColumnName = "GPA_PROD_NO")
    private List<GpaProdPics> gpaProdPics = new LinkedList<GpaProdPics>();
    
    @OneToMany(mappedBy = "gpaProd")
    private List<GpaSo> gpaSo;
    
    @OneToMany
    @JoinColumn(name = "GPA_PROD_NO",
    referencedColumnName = "GPA_PROD_NO")
    private List<GpaReach> gpaReach = new LinkedList<GpaReach>();
    
//    @OneToMany
//    @JoinColumn(name = "GPA_PROD_NO",
//    referencedColumnName = "GPA_PROD_NO")
//    private List<GpaSo> gpaSo;
}
