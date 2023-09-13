package web.front_end.member.forum.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

import javax.persistence.*;
import core.entity.Core;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ARTICLE_COLL_LIST")
public class ArticleCollList extends Core {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "MEMBER_NO")
	private Integer memberNo;
	
	@Id
	@Column(name = "ARTICLE_NO")
	private Integer articleNo;

	}


