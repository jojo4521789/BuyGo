package web.front_end.seller.gpa.prod.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GPA_REACH")
public class GpaReach extends Core{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GPA_REACH_NO")
    private Integer gpaReachNo;

    @Column(name = "GPA_PROD_NO")
    private Integer gpaProdNo;

    @Column(name = "GPA_LEVEL_COUNT")
    private Integer gpaLevelCount;

    @Column(name = "GPA_LEVEL_PRICE")
    private Integer gpaLevelPrice;
}
