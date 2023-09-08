package web.front_end.member.gpa.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GpaProdRp extends Core {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GPA_PROD_RP_NO")
	private Integer gpaProdRpNo;
	@Column(name = "GPA_PROD_NO")
	private Integer gpaProdNo;
	@Column(name = "MEMBER_NO")
	private Integer memberNo;
	@Column(name = "GPA_PROD_RP_CONTENT")
	private String gpaProdRpContent;
	@Column(name = "GPA_PROD_RP_REASON")
	private Integer gpaProdRpReason;

}
