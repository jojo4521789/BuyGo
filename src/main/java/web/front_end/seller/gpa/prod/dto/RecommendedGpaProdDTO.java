package web.front_end.seller.gpa.prod.dto;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.front_end.member.acc.entity.Member;
import web.front_end.seller.gpa.order.entity.GpaSo;
import web.front_end.seller.gpa.prod.entity.GpaProdPics;
import web.front_end.seller.gpa.prod.entity.GpaReach;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendedGpaProdDTO extends Core{
	private static final long serialVersionUID = 1L;

	private Integer gpaProdNo;
	
    private String gpaProdName;

    private Integer gpaFirstPrice;
    
    private Integer gpaPreProd;

    private Timestamp gpaEndDate;
    
    private String gpaProdPicToBase64;
    
    private List<GpaReach> gpaReach = new LinkedList<GpaReach>();
}
