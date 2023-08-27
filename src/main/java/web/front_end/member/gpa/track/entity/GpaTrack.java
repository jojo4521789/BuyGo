package web.front_end.member.gpa.track.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class GpaTrack extends Core{
	@Id
	@Column(name = "GPA_PROD_NO")
    private int gpaProdNo;
    @Id
    @Column(name = "MEMBER_NO")
	private int memberNo;


}
