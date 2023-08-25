package web.front_end.member.pa.prodpic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import core.pojo.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProdPic extends Core {

	private static final long serialVersionUID = 8042542712391581786L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PA_PROD_PIC_NO", insertable = false)
	private Integer paProdPicNo;
	@Column(name = "PA_PROD_NO", insertable = false)
	private Integer paProdNo;
	@Column(name = "PA_PROD_PIC")
	private byte[] paProdPic;
}
