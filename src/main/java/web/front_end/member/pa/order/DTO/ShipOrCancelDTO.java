package web.front_end.member.pa.order.DTO;

import lombok.Data;

@Data
public class ShipOrCancelDTO {
	Integer paSoNo;
	Byte newStatus;
	Integer buyerNo;
	Integer refundToBuyer;
	String deliverMsg;
}
