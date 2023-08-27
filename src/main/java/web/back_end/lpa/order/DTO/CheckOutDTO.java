package web.back_end.lpa.order.DTO;

import java.util.List;

import lombok.Data;
import web.back_end.lpa.order.entity.LpaSo;
import web.back_end.lpa.order.entity.LpaSoDetails;

@Data
public class CheckOutDTO {
	private LpaSo lpaSo;
	private List<LpaSoDetails> lpaSoDetails;
}
