package web.front_end.member.forum.dto;

import javax.persistence.Entity;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.front_end.member.forum.entity.ArticleCollList;
import web.front_end.member.forum.entity.ArticleMsg;
import web.front_end.member.forum.entity.ForumArticle;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ArticlelListDTO extends Core {
	private static final long serialVersionUID = 1L;
	
	private ForumArticle forumArticle = new ForumArticle();
	private ArticleMsg articleMsg = new ArticleMsg();
	private ArticleCollList articleCollList = new ArticleCollList();
}
