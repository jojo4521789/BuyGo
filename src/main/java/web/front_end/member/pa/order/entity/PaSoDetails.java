package web.front_end.member.pa.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.front_end.member.pa.prod.entity.PaProd;

@Entity
@Table(name = "pa_so_details")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaSoDetails extends Core implements Serializable {
	@Id
	@Column(name = "PA_SO_NO")
	private Integer paSoNo;
	@Id
	@Column(name = "PA_PROD_NO")
	private Integer paProdNo;

	@Column(name = "PA_PROD_NAME")
	private String paProdName; // Alan add
	@Column(name = "PA_PROD_PRICE")
	private Integer paProdPrice;
//	@Column(name = "PA_PROD_QTY")
//	private Integer paProdQty; // Alan add
	@Column(name = "PA_ORD_QTY")
	private Integer paOrdQty;
	@Column(name = "STATUS", insertable = false)
	private Integer status;
	// 參考PaProd(一個明細對應到一個商品,一個商品可以對應到多個明細)
	@ManyToOne
	@JoinColumn(name = "PA_PROD_NO", insertable = false, updatable = false)
	private PaProd paProd;
}
