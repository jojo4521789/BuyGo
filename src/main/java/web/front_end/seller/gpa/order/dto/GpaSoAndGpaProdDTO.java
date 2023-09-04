package web.front_end.seller.gpa.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.front_end.seller.gpa.order.entity.GpaSo;
import web.front_end.seller.gpa.prod.entity.GpaProd;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GpaSoAndGpaProdDTO {
	// GpaSo
    private GpaSo gpaSo = new GpaSo();

    // GpaProd
    private GpaProd gpaProd = new GpaProd();
}
