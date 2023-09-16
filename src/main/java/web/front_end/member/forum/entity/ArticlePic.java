package web.front_end.member.forum.entity;

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
@Table(name = "ARTICLE_PIC")
public class ArticlePic extends Core {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARTICLE_PIC_NO")
	private Integer articlePicNo;

	@Column(name = "ARTICLE_NO")
	private Integer article;

	@Column(name = "ARTICLE_PIC")
	private byte[] articlePic;

}
