package web.front_end.member.black.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlacklistDTO{
	private static final long serialVersionUID = 1L;

	private Integer blackNo;
	
	private Integer memberNo;
	
	private Integer memberNoBlack;
	
	private String memberAcctBlack;
}