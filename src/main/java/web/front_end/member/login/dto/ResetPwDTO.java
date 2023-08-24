package web.front_end.member.login.dto;

import java.io.Serializable;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResetPwDTO extends Core{
	private static final long serialVersionUID = 1L;
	
	private String memberAcct;
	private String memberEmail;
	private String authCode;
	private String memberPw;
	
}
