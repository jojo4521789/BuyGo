package web.back_end.opa.coupon.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import core.entity.Core;
import lombok.Data;

@Data
@Entity
@Table(name = "OPA_CPOWNER")
public class CpOwner extends Core{
	private static final long serialVersionUID = -4315304606727919277L;
	@EmbeddedId
	private CpOwnerId cpOwnerId;
	@Column(name = "OPA_CPOWNER_STATUS")
	private Integer opaCpownerStatus;
	
}
