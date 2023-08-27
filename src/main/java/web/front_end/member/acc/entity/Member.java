package web.front_end.member.acc.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

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
public class Member extends Core {
	private static final long serialVersionUID = 1457755989409740329L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_NO")
	private Integer memberNo;
	@Column(name = "MEMBER_ACCT")
	private String memberAcct;
	@Column(name = "MEMBER_PW")
	private String memberPw;
	@Column(name = "MEMBER_STATUS")
	private Integer memberStatus;
	@Column(name = "MEMBER_NAME")
	private String memberName;
	@Column(name = "MEMBER_ADD")
	private String memberAdd;
	@Column(name = "MEMBER_PHONE")
	private String memberPhone;
	@Column(name = "MEMBER_EMAIL")
	private String memberEmail;
	@Column(name = "MEMBER_GENDER")
	private Integer memberGender;
	@Column(name = "MEMBER_BIRTHDAY")
	private String memberBirthday;
	@Column(name = "MEMBER_ID")
	private String memberId;
	@Column(name = "MEMBER_EVA_STAR", insertable = false)
	private Integer memberEvaStar;
	@Column(name = "MEMBER_EVA_COUNT", insertable = false)
	private Integer memberEvaCount;
	@Column(name = "SELLER_EVA_STAR", insertable = false)
	private Integer sellerEvaStar;
	@Column(name = "SELLER_EVA_COUNT", insertable = false)
	private Integer sellerEvaCount;

	

}
