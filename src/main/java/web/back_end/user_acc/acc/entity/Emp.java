package web.back_end.user_acc.acc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.pojo.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EMP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emp extends Core {
	private static final long serialVersionUID = -4387917041108258272L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_NO")
	private Integer empNo;
	@Column(name = "EMP_NAME")
	private String empName;
	@Column(name = "EMP_GENDER")
	private Integer empGender;
	@Column(name = "EMP_TEL")
	private String empTel;
	@Column(name = "EMP_MAIL")
	private String empMail;
	@Column(name = "EMP_ACCT")
	private String empAcct;
	@Column(name = "EMP_PW")
	private String empPw;
	@Column(name = "EMP_STATE")
	private boolean empState;
}
