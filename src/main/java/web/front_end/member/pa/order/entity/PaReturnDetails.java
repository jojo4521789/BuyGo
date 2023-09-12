package web.front_end.member.pa.order.entity;

import java.sql.Timestamp;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "pa_return_details")
@Data
public class PaReturnDetails implements Serializable {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PA_RTN_NO")
	private Integer paRtnNo;
	@Id
	@Column(name = "PA_RTN_PROD_NO")
    private Integer paRtnProdNo;
	@Column(name = "PA_RTN_PROD_NAME")
    private String paRtnProdName;

}
