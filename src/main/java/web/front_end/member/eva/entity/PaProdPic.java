package web.front_end.member.eva.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "PA_PROD_PIC")
public class PaProdPic extends Core{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PA_PROD_PIC_NO")
    private Integer paProdPicNo;

    @Column(name = "PA_PROD_NO")
    private Integer paProdNo;

    @Column(name = "PA_PROD_PIC")
    private byte[] paProdPic;
    
    @Transient
    private String paProdPicToBase64;
}