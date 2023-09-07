package web.front_end.member.chat.entity;

import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class State {
	private String type;
	private String memberNo;
	private String memberAcct;
	// total users
//	private Set<String> users;
	private Map<String,String> members;
//	private Set<String> memberNos;
	
//	private Set<String> memberAccts;

	public State(String type, String memberNo, Map<String, String> members) {
		super();
		this.type = type;
		this.memberNo = memberNo;
		this.members = members;
//		this.memberAccts = memberAccts;
	}
	
//	public State(String type, String memberNo, Set<String[]> members) {
//		super();
//		this.type = type;
//		this.memberNo = memberNo;
//		this.members = members;
//	}
	
}
