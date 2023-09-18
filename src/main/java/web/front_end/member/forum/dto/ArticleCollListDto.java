package web.front_end.member.forum.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.front_end.member.forum.entity.ArticleCollList;
import web.front_end.member.forum.entity.ArticleMsg;
import web.front_end.member.forum.entity.ForumArticle;


public class ArticleCollListDto extends ForumArticle {
	private List<ArticleCollList> lstCollLists;
	private List<ForumArticle> lstForumArticle;

	public List<ForumArticle> getLstForumArticle() {
		return lstForumArticle;
	}

	public void setLstForumArticle(List<ForumArticle> lstForumArticle) {
		this.lstForumArticle = lstForumArticle;
	}

	public List<ArticleCollList> getLstCollLists() {
		return lstCollLists;
	}

	public void setLstCollLists(List<ArticleCollList> lstCollLists) {
		this.lstCollLists = lstCollLists;
	}
}
