package web.back_end.lpa.product.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lpa_so_pic")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LpaProdPic implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LPA_PROD_PIC_NO")
	private Integer lpaProdPicNo;
	@Column(name = "LPA_PROD_NO")
	private Integer lpaProdNo;
	@Column(name = "LPA_PROD_PIC")
	private byte[] lpaProdPic;
}
