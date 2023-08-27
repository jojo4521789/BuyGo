package web.back_end.lpa.product.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.back_end.lpa.order.entity.LpaSo;

@Entity
@Table(name = "lpa_prod")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LpaProd extends Core implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LPA_PROD_NO")
	private Integer lpaProdNo;
	@Column(name = "MEMBER_NO", updatable = false)
	private Integer memberNo;
	@Column(name = "LPA_PROD_NAME")
	private String lpaProdName;
	@Column(name = "LPA_PROD_CONTENT")
	private String lpaProdContent;
	@Column(name = "LPA_PROD_CATS_NO", updatable = false)
	private Integer lpaProdCatsNo;
	@Column(name = "LPA_PROD_STOCK")
	private Integer lpaProdStock;
	@Column(name = "LPA_PROD_SOLD")
	private Integer lpaProdSold;
	@Column(name = "LPA_PROD_PRICE")
	private Integer lpaProdPrice;
	@Column(name = "LPA_PROD_OFF_TIME")
	private Timestamp lpaProdOffTime;
	@Column(name = "LPA_PROD_STATUS", insertable = false)
	private Integer lpaProdStatus;
//	@ManyToMany(mappedBy = "lpaProds")
//	private List<LpaSo> lpaSos;

	// Constructor
	
}
