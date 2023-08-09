package web.back_end.user_acc.func.entity;

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
@Table(name = "EMP_FUN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpFun extends Core {

	private static final long serialVersionUID = 5082082520761575820L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_NO")
	private Integer empNO;
	@Column(name = "FUN_NO")
	private Integer funNo;
}
