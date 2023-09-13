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
@Table(name = "article_record")
public class ArticleRecord extends Core {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "MEMBER_NO")
	private Integer memberNo;
	
	@Id
	@Column(name = "ARTICLE_NO")
	private Integer articleNo;

	@Column(name = "ACTICLE_RECORD_STAT")
	private Integer articleRecordStat;
	
}
