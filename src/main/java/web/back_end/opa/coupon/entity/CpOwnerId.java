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
	
	@Override
	public int hashCode() {
		return Objects.hash(memberNo, opaCouponNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CpOwnerId other = (CpOwnerId) obj;
		return Objects.equals(memberNo, other.memberNo) && Objects.equals(opaCouponNo, other.opaCouponNo);
	}
}
