package web.front_end.member.opa.order.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Order {
    private Integer opaSoNo;
	private String opaSoStatus;
	private Date opaSoDate;
	private double opaProdTotal;
	private double opaDiscount;
	private Integer opaFirAmount;
	private Integer opaSecAmount;
	private Integer opaTotal;
	private Integer opaRealTotal;
	private String opaRealStatus;
	private String opaFailedReason;
}
