package web.back_end.opa.prod.entity;

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
@Table(name = "OPA_PRPICS")
public class Prpics extends Core{
	private static final long serialVersionUID = -8021442954129156916L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OPA_PRPICS_NO")
	private Integer opaPrpicsNo;
	@Column(name = "OPA_PROD_NO")
	private Integer opaProdNo;
	@Column(name = "OPA_PROD_PICTURE")
	private byte[] opaProdPicture;
}
