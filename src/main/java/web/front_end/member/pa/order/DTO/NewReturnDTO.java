package web.front_end.member.pa.order.DTO;

import java.util.List;

import lombok.Data;
import web.front_end.member.pa.order.entity.PaReturn;

@Data
public class NewReturnDTO {
	PaReturn paReturn;
	List<String> paRtnImages;
	List<Integer> paRtnProdNoList;
}
