package web.front_end.member.eva.dto;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GpaProdEvaDTO {
	// GpaProd
	private String gpaProdName;
	
	private Timestamp gpaEndDate;
	// GpaSo
    private List<Integer> gpaSoNo = new LinkedList<Integer>();;

    private List<Integer> gpaProdCount = new LinkedList<Integer>();;

    private List<Integer> gpaProdTotal = new LinkedList<Integer>();;

    private List<Integer> gpaEvaSeller = new LinkedList<Integer>();;
    // member
	private List<String> memberAcct = new LinkedList<String>();;
	// GpaProdPics
	private List<String> gpaProdPics = new LinkedList<String>();;
}
