package web.back_end.lpa.product.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Lpa_Prod implements Serializable {
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

	// Constructor
	
}
