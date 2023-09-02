package web.back_end.lpa.order.DTO;

import lombok.Data;

@Data
public class ShipOrCancelDTO {
	Integer lpaSoNo;
	Byte newStatus;
	Integer buyerNo;
	Integer refundToBuyer;
}
