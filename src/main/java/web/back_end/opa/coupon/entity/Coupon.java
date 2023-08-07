package web.back_end.opa.coupon.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "OPA_COUPON")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon extends Core{
	private static final long serialVersionUID = 1143564645975074532L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OPA_COUPON_NO")
	private Integer opaCouponNo;
	@Column(name = "OPA_COUPON_NAME")
	private String opaCouponName;
	@Column(name = "OPA_DISCOUNT_AMO")
	private Integer opaDiscountAmo;
	@Column(name = "OPA_MIN_AMOUNT")
	private Integer opaMinAmount;
	@Column(name = "OPA_EXP_DATE")
	private Timestamp opaExpDate;
}
