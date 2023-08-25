package web.back_end.lpa.order.DTO;

import java.util.List;

import lombok.Data;
import web.back_end.lpa.order.entity.LpaSoDetails;

@Data
public class EditProdStatusDTO {
	private List<LpaSoDetails> lpaSoDetailsList;
}
