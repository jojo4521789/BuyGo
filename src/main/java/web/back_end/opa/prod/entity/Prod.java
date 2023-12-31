package web.back_end.opa.prod.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.entity.Core;
import lombok.Data;

@Data
@Entity
@Table(name = "OPA_PRODUCTS")
public class Prod extends Core{
	private static final long serialVersionUID = 1346237891643762428L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OPA_PROD_NO")
	private Integer opaProdNo;
	@Column(name = "OPA_PRCATS_NO")
	private Integer opaPrcatsNo;
	@Column(name = "OPA_PROD_NAME")
	private String opaProdName;
	@Column(name = "OPA_PROD_STOCK_QTY")
	private Integer opaProdStockQty;
	@Column(name = "OPA_PROD_SHIP_QTY")
	private Integer opaProdShipQty;
	@Column(name = "OPA_PROD_PRICE")
	private Double opaProdPrice;
	@Column(name = "OPA_PROD_CONTENT")
	private String opaProdContent;
	@Column(name = "OPA_PROD_URL")
	private String opaProdUrl;
	@Column(name = "OPA_PROD_STATUS", insertable = false)
	private Integer opaProdStatus;
	@Column(name = "OPA_PROD_UPDATE", insertable = false, updatable = false)
	private Timestamp opa_prod_update;
	
}
