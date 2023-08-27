package web.front_end.guest.news.entity;

import java.sql.Blob;
import java.sql.Timestamp;

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
public class News extends Core {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NEWS_NO")
	private Integer newsNo;
	@Column(name = "NEWS_TITLE")
	private String newsTitle;
	@Column(name = "NEWS_CONTENT")
	private String newsContent;
	@Column(name = "NEWS_TIME" ,insertable = false)
	private Timestamp newsTime;
	@Column(name = "NEWS_PICTURE")
	private String newsPicture;
}
