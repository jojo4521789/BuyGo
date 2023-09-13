package web.front_end.member.forum.entity;

import java.sql.Timestamp;

import javax.persistence.*;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RP_ARTICLE_MSG")
public class RpArticleMsg extends Core{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RP_MSG_NO")
	private Integer rpMsgNo;

	// 這裡需要修改"關聯"
	@Column(name = "MSG_NO")
	private Integer msgeNo;

	// 這裡需要修改"關聯"
	@Column(name = "RP_MEMBER_NO")
	private Integer rpMemberNo;

	@Column(name = "RP_CONTENT", columnDefinition = "TEXT")
	private String rpContent;

	@Column(name = "RP_DATE", insertable = false, updatable = false)
	private Timestamp rpDate;

	@Column(name = "MSG_STAT", insertable = false, columnDefinition = "TINYINT default 0")
	private Integer msgeStat;

	@Column(name = "AFREP_RESULT", insertable = false, columnDefinition = "TINYINT default 0")
	private Integer auditResult;
}
