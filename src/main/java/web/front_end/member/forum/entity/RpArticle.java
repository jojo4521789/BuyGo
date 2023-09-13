package web.front_end.member.forum.entity;

import java.sql.Date;
import java.sql.Timestamp;

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

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RP_ARTICLE")

public class RpArticle extends Core {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RP_ARTICLE_NO")
	private Integer rpArticleNo;
	
	//這裡需要修改"關聯"
	// @ManyToOne
	// @JoinColumn(name = "ARTICLE_NO")
	@Column(name = "ARTICLE_NO")
	private Integer articleNo;
	//private ForumArticle forumArticle;
	
	//這裡需要修改"關聯"
	@Column(name = "MEMBER_NO")
	private Integer rpmemberNo;

	@Column(name = "RP_CONTENT", columnDefinition = "TEXT")
	private String reportContent;

	@Column(name = "RP_DATE", insertable = false, updatable = false)
	private Timestamp reportDate;

	@Column(name = "ARTICLE_STAT", insertable = false, columnDefinition = "TINYINT default 0")
	private Integer articleStatus;

	@Column(name = "AFREP_RESULT", insertable = false, columnDefinition = "TINYINT default 0")
	private Integer auditResult;

}
