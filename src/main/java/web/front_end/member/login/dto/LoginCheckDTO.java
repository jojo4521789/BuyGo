package web.front_end.member.login.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginCheckDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String memberAcct;
	private boolean loginState;
	
}
