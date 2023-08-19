package web.back_end.lpa.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.back_end.lpa.product.entity.LpaProdPic;

@Entity
@Table(name = "lpa_so_details")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LpaSoDetails implements Serializable{
	@Id
	@Column(name = "LPA_SO_NO")
	private Integer lpaSoNo;
	@Id
	@Column(name = "LPA_PROD_NO")
	private Integer lpaProdNo;
	@Column(name = "LPA_PROD_PRICE")
	private Integer lpaProdPrice;
	@Column(name = "LPA_ORD_QTY")
	private Integer lpaOrdQty;
//	@OneToMany
//	@JoinColumn(name = "LPA_PROD_NO", referencedColumnName = "LPA_PROD_NO")
//	private LpaProdPic lpaProdPic;
}
