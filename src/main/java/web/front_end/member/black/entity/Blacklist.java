package web.front_end.member.black.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.front_end.member.acc.entity.Member;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BLACKLIST")
public class Blacklist extends Core{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BLACK_NO")
	private Integer blackNo;

	@Column(name = "MEMBER_NO")
	private Integer memberNo;

	@Column(name = "MEMBER_NO_BLACK")
	private Integer memberNoBlack;
	
	// 取回被黑名單者的會員資料
    @ManyToOne
    @JoinColumn(name = "MEMBER_NO_BLACK",
    insertable = false, updatable = false)
    private Member member;
}