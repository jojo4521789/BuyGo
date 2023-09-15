package web.front_end.member.forum.dto;

import java.util.List;

import web.front_end.member.acc.entity.Member;
import web.front_end.member.forum.entity.ArticleCollList;
import web.front_end.member.forum.entity.ArticleMsg;
import web.front_end.member.forum.entity.ForumArticle;

public class ForumArticleDTO extends ForumArticle {
	private int likeCount;
	private int unLikeCount;
	private int selfFavStatus;
	private int isCollect; // 1 , 0
	private List<ArticleMsg> lstMsg;
	private List<ForumArticle> lstForumArticle;
	private String memberAcct;

	public String getMemberAcct() {
		return memberAcct;
	}

	public void setMemberAcct(String memberAcct) {
		this.memberAcct = memberAcct;
	}

	public List<ForumArticle> getLstForumArticle() {
		return lstForumArticle;
	}

	public void setLstForumArticle(List<ForumArticle> lstForumArticle) {
		this.lstForumArticle = lstForumArticle;
	}

	private List<ArticleCollList> lstCollLists;
	private List<Member> members;

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public List<ArticleCollList> getLstCollLists() {
		return lstCollLists;
	}

	public void setLstCollLists(List<ArticleCollList> lstCollLists) {
		this.lstCollLists = lstCollLists;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getUnLikeCount() {
		return unLikeCount;
	}

	public void setUnLikeCount(int unLikeCount) {
		this.unLikeCount = unLikeCount;
	}

	public int getSelfFavStatus() {
		return selfFavStatus;
	}

	public void setSelfFavStatus(int selfFavStatus) {
		this.selfFavStatus = selfFavStatus;
	}

	public int getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(int isCollect) {
		this.isCollect = isCollect;
	}

	public List<ArticleMsg> getLstMsg() {
		return lstMsg;
	}

	public void setLstMsg(List<ArticleMsg> lstMsg) {
		this.lstMsg = lstMsg;

	}

}
