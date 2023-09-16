package web.front_end.member.pa.order.DTO;

import java.util.List;

import lombok.Data;
import web.front_end.member.pa.order.entity.PaSoDetails;

@Data
public class EditProdStatusDTO {
	private List<PaSoDetails> paSoDetailsList;
}
