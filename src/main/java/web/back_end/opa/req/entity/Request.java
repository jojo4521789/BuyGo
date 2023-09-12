package web.back_end.opa.req.entity; 

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Request {
    private Integer opaRequestNo;
	private String opaRequestProductsName;
	private String opaRequestProductsUrl;
	private String opaRequestProductsContent;
	private String opaRequestStatus;
	private Date opaRequestStartdate;
	private String opaFailedReason;
}
