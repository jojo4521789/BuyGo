package web.front_end.member.pa.order.DTO;

import java.util.List;

import lombok.Data;
import web.front_end.member.pa.order.entity.PaSo;
import web.front_end.member.pa.order.entity.PaSoDetails;

@Data
public class CheckOutDTO {
	private PaSo paSo;
	private List<PaSoDetails> paSoDetails;
}
