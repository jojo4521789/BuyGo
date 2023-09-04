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
public class PaSoEvaDTO {
	// PaSo
	private Integer paSoNo;
	
	private Integer paSoStatus;
	
	private Integer paSoTotal;
	
	private Integer paEvaSeller;
	
	// Member
	private String memberAcct;
	
	// PaSoDetails
	private List<PaSoDetailsEvaDTO> paSoDetailsEvaDTO = new LinkedList<PaSoDetailsEvaDTO>();
}
