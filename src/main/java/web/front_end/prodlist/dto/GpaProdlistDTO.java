package web.front_end.prodlist.dto;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.front_end.seller.gpa.prod.entity.GpaProdPics;
import web.front_end.seller.gpa.prod.entity.GpaReach;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GpaProdlistDTO extends Core{
	
	private static final long serialVersionUID = 174526063296889502L;
	
	private Integer gpaProdNo;
	private Integer memberNo;
	private String memberAcct;
	private Integer gpaCatsNo;
	private String gpaProdName;
	private Integer gpaFirstPrice;
	private Integer gpaMiniCount;
	private Integer gpaMaxCount;
	private Integer gpaPreProd;
	private String gpaProdContent;
	private Timestamp gpaEndDate;
	
	private List<GpaProdPics> gpaProdPics = new LinkedList<GpaProdPics>();
	
	private List<GpaReach> gpaReach = new LinkedList<GpaReach>();
}
