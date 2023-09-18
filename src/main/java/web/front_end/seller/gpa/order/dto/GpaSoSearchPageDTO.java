package web.front_end.seller.gpa.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GpaSoSearchPageDTO {
    private Integer limit;
    
    private Integer offset;
    
    private Integer gpaSoStat;
    
    private String searchStr;
}
