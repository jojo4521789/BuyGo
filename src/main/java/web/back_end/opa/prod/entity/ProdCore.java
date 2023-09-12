package web.back_end.opa.prod.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProdCore implements Serializable{
	private static final long serialVersionUID = -8622153011857005360L;
	private boolean successful;
	private String message;
	private String action;
	private Integer limit;
	private Integer offset;
}
