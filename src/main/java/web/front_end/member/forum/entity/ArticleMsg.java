package web.front_end.member.forum.entity;

import javax.persistence.*;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ARTICLE_MSG")

public class ArticleMsg extends Core {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MSG_NO")
	private Integer messageNumber;

	@Column(name = "ARTICLE_NO")
	private Integer article;

	@Column(name = "MEMBER_NO")
	private Integer member;

	@Column(name = "MSG_CONTENT")
	private String content;

	@Column(name = "MSG_TIME", insertable = false)
	private Timestamp messageTime;

	@Column(name = "MSG_STAT", nullable = false, columnDefinition = "TINYINT default 0")
	private Integer messageStatus;

	@Column(name = "MSG_UPDATE", insertable = false)
	private Timestamp messageUpdate;

}
