package infostaff.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@ToString 
public class StaffLeavingSearchModel {

	private Long staffId;
	
	private Date fromDate;
	
	private Date toDate;
	
	private String approveStatus;
	
}
