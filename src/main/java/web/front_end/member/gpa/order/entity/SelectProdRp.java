package web.front_end.member.gpa.order.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SelectProdRp extends GpaProdRp{
	
	 	private Integer gpaProdNo;

	    private Integer memberNo;

	    private Integer gpaCatsNo;

	    private String gpaProdName;
	    
	    private String gpaProdContent;

	    private Integer paProdNo;

	    private Integer paProdObjNo;

	    private String paProdName;

	    private String paProdContent;
	    
		private Integer gpaProdRpReason;
		
		private String gpaProdRpContent;
		private Integer prodSell;
		private Integer gpaProdRpNo;

	    public  SelectProdRp(Object[]  objects)  {
			this.gpaProdNo = (Integer) objects[0];
			this.memberNo = (Integer) objects[1];
			this.gpaCatsNo = (Integer) objects[2];
			this.gpaProdName = (String) objects[3];
			this.gpaProdContent = (String) objects[4];
			this.gpaProdRpReason =((int)(Byte) objects[5]);
			this.gpaProdRpContent = (String) objects[6];
			this.prodSell =((int) (Byte) objects[7]);
			this.gpaProdRpNo = (Integer) objects[8];
		
		}

}
