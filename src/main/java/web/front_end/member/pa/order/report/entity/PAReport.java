package web.front_end.member.pa.order.report.entity;

import java.sql.Timestamp;

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
@Table(name = "PA_RP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PAReport extends Core {

	private static final long serialVersionUID = -8942727920170426600L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PA_RP_NO")
	private Integer paRpNo;
	@Column(name = "PA_SO_NO")
	private Integer paSoNo;
	@Column(name = "MEMBER_NO")
	private Integer memberNo;
	@Column(name = "PA_RP_STAT")
	private String paRpStat;
	@Column(name = "PA_STARTDATE")
	private Timestamp paStartDate;
	@Column(name = "PA_PROD_RP_REASON")
	private Integer paProdRpReason;

}
