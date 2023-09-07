package web.front_end.member.eva.dto;

import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.front_end.member.eva.entity.PaSo;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaSoDetailsEvaDTO {
	// PaSoDetails
	private String paProdName;
	
	private Integer paProdQty;
	
	private Integer paProdPrice;
	
	// PaProdPics
	private List<String> paProdPicToBase64 = new LinkedList<String>();
}
