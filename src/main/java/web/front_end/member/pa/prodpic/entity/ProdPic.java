package web.front_end.member.pa.prodpic.entity;

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
@Setter
@Getter
@Table(name = "PA_PROD_PIC")
@NoArgsConstructor
@AllArgsConstructor
public class ProdPic extends Core {

	private static final long serialVersionUID = 8042542712391581786L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PA_PROD_PIC_NO", insertable = false)
	private Integer paProdPicNo;
	@Column(name = "PA_PROD_NO")
	private Integer paProdNo;
	@Column(name = "PA_PROD_PIC")
	private String paProdPic;
}
