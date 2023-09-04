package web.front_end.member.forum.entity;

import java.sql.Timestamp;
import javax.persistence.*;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FORUM_ARTICLE")
public class ForumArticle extends Core {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARTICLE_NO")
	private Integer articleNo;

	@Column(name = "MEMBER_NO")
	private Integer memberNo;

	@Column(name = "ARTICLE_TITLE")
	private String articleTitle;

	@Column(name = "ARTICLE_CONTENT", columnDefinition = "TEXT")
	private String articleContent;

	@Column(name = "ARTICLE_STATUS", insertable = false,  columnDefinition = "TINYINT default 0")
	private Integer articleStatus;

	@Column(name = "ARTICLE_PUBLISH", insertable = false, updatable = false)
	private Timestamp articlePublish;

	@Column(name = "ARTICLE_UPDATE", insertable = false, updatable = false)
	private Timestamp articleUpdate;

	@Column(name = "ARTICLE_LIKE", insertable = false)
	private Integer articleLike;

	@Column(name = "ARTICLE_DISLIKE", insertable = false)
	private Integer articleDislike;

}
