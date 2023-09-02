package web.back_end.opa.coupon.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class CpOwnerId implements Serializable{
	@Column(name = "OPA_COUPON_NO")
	private Integer opaCouponNo;
	@Column(name = "MEMBER_NO")
	private Integer memberNo;
}
