package web.front_end.seller.gpa.order.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GpaOrderDTO {
	// GpaSo
    private Integer gpaSoNo;

    private Integer gpaProdCount;

    private Integer gpaProdTotal;

    private Integer gpaEvaSeller;

    // GpaProd
    private String gpaProdName;

    private Timestamp gpaEndDate;
    // member
	private String memberAcct;
	// GpaProdPics
	private List<String> gpaProdPics;
}
