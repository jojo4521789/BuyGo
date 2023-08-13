package web.front_end.prodlist.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.entity.Core;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "PA_PROD", catalog = "buygo")
public class Prodlist extends Core{
	
	private static final long serialVersionUID = 174526063296889502L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PA_PROD_NO")
	private Integer paProdNo;
	@Column(name = "MEMBER_NO")
	private Integer memberNo;
	@Column(name = "PA_PROD_OBJ_NO")
	private Integer paProdObjNo;
	@Column(name = "PA_PROD_NAME")
	private String paProdName;
	@Column(name = "PA_PROD_PRICE")
	private Integer paProdPrice;
	@Column(name = "PA_PROD_STOCK_QTY")
	private Integer paProdStockQty;
	@Column(name = "PA_PROD_SHIP_QTY")
	private Integer paProdShipQty;
	@Column(name = "PA_PROD_UPDATE", insertable = false)
	private Timestamp paProdUpdate;
	@Column(name = "PA_PROD_CONTENT")
	private String paProdContent;
	@Column(name = "PA_PROD_STATUS")
	private Integer paProdStatus;

}
