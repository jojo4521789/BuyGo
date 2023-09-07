package web.front_end.member.pa.req.dto;

import java.sql.Timestamp;

import javax.persistence.Entity;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JoinSellerNameDTO extends Core{
	
	private static final long serialVersionUID = 1437964681301290044L;
	
	//MPaReq Entity
	private Integer paRqNo;
    private Integer memberNoMember;
    private Integer memberNoSeller;
    private String paRqProdName;
    private String paRqUrl;
    private Integer paRqPrice;
    private Integer paRqQty;
    private String paRqNote;
    private Integer paRqStat;
	private Timestamp paRqStartDate;
	
	//Member Entity
	private Integer memberNo;
	private String memberAcct;
	
}
