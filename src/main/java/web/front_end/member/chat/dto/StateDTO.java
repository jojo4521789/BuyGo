package web.front_end.member.chat.dto;

import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StateDTO {
	private String type;
	private String memberNo;
	private String memberAcct;
	private Map<String,String[]> members;

	public StateDTO(String type, String memberNo, Map<String, String[]> members) {
		super();
		this.type = type;
		this.memberNo = memberNo;
		this.members = members;
	}
	
}
