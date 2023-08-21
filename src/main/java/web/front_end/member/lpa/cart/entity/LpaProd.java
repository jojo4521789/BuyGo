package web.front_end.member.lpa.cart.entity;

import java.sql.Timestamp;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "LPA_PROD")
public class LpaProd {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LPA_PROD_NO")
	private Integer lpaProdNo;

	@Column(name = "MEMBER_NO")
	private Integer memberNo;

	@Column(name = "LPA_PROD_NAME", length = 100, nullable = false)
	private String lpaProdName;

	@Column(name = "LPA_PROD_CONTENT", columnDefinition = "TEXT")
	private String lpaProdContent;

	@Column(name = "LPA_PROD_CATS_NO", nullable = false)
	private Integer lpaProdCatsNo;

	@Column(name = "LPA_PROD_STOCK")
	private Integer lpaProdStock;

	@Column(name = "LPA_PROD_SOLD")
	private Integer lpaProdSold;

	@Column(name = "LPA_PROD_PRICE")
	private Integer lpaProdPrice;

	@Column(name = "LPA_PROD_OFF_TIME")
	private Timestamp lpaProdOffTime;

	@Column(name = "LPA_PROD_STATUS", nullable = false, columnDefinition = "INT default 0")
	private Integer lpaProdStatus;

}
