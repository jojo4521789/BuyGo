package web.back_end.opa.coupon.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CpOwnerId implements Serializable{
	@Column(name = "OPA_COUPON_NO")
	private Integer opaCouponNo;
	@Column(name = "MEMBER_NO")
	private Integer memberNo;
}
