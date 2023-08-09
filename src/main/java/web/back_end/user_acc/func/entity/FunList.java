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
@Table(name = "FUN_LIST")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FunList extends Core{
	private static final long serialVersionUID = -3481733577296185311L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FUN_NO")
	private Integer funNo;
	@Column(name = "FUN_NAME")
	private String funName;
	
	
}
