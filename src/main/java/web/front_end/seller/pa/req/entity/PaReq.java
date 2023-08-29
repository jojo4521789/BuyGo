package web.front_end.seller.pa.req.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.entity.Core;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="PA_REQUEST", catalog = "buygo")
public class PaReq extends Core {
	
	private static final long serialVersionUID = 7040173214235867311L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="PA_RQ_NO")
    private Integer paRqNo;
	@Column(name ="MEMBER_NO_MEMBER")
    private Integer memberNoMember;
	@Column(name ="PA_RQ_PROD_NAME")
    private String paRqProdName;
	@Column(name ="PA_RQ_URL")
    private String paRqUrl;
	@Column(name ="PA_RQ_STAT")
    private String paRqStat;

}
