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
public class PaProdEvaDTO {
	// PaProd
//	private String paProdName;
	
	// PaSo
    private List<PaSo> paSo = new LinkedList<PaSo>();
    
	// PaProdPics
	private List<String> paProdPics = new LinkedList<String>();
}
