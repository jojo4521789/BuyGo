package web.back_end.login.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpFunctionCheckDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer empNo;
	private String empAcct;
	private boolean loginState;	
	private Integer empFun;
}
