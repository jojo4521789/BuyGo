package web.front_end.seller.gpa.prod.entity;


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
@Table(name = "GPA_PROD_PICS")
public class GpaProdPics extends Core{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GPA_PROD_PIC_NO")
    private Integer gpaProdPicNo;

    @Column(name = "GPA_PROD_NO")
    private Integer gpaProdNo;

    @Column(name = "GPA_PROD_PIC")
    private byte[] gpaProdPic;
    
    @Transient
    private String gpaProdPicToBase64;
}
