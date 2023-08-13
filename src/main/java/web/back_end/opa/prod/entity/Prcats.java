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
@Table(name = "OPA_PRCATS")
public class Prcats extends Core{
	private static final long serialVersionUID = 1329886329266797536L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OPA_PRCATS_NO")
	private Integer opaPrcatsNo;
	@Column(name = "OPA_PRCATS_NAME")
	private String opaPrcatsName;
}
