package web.front_end.member.pa.order.requst.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.pojo.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PA_REQUEST")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PARequest extends Core {

	private static final long serialVersionUID = 8587621026559131854L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PA_RQ_NO")
	private Integer paRqNo;
	@Column(name = "MEMBER_NO_MEMBER")
	private Integer memberNoMember;
	@Column(name = "PA_RQ_PROD_NAME")
	private String paRqProdName;
	@Column(name = "PA_RQ_STAT")
	private String paRqStat;

}
