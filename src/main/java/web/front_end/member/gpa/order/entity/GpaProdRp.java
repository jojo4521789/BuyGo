package web.front_end.member.gpa.order.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "GPA_PROD_RP")
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
	@Column(name = "PA_PROD_NO")
	private Integer paProdNo;
	//關連到PA_PROD_NO
	@Column(name = "PROD_SELL")
	private Integer prodSell;
	
	

}
