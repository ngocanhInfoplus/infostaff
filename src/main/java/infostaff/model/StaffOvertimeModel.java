package infostaff.model; 

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString; 



@Getter 
@Setter 
@ToString 
public class StaffOvertimeModel{ 
	
	private Long id;
	
	private StaffModel staffModel;
	//private Long staffId; 

	private Date registerDate; 

	private String fromTime; //hh:mm:ss

	private String toTime; //hh:mm:ss

	private String projectName; 

	//private String otType;
	private OvertimeTypeModel otType;

	private String approveStatus; 
	
	private String approveStatusName;

	private Long managerId01; 

	private Long managerId02; 

	private String createdUser; 

	private Date createdDate; 

	private String changedUser; 

	private Date changedDate; 

	private String approvedUser; 

	private Date approvedDate; 

	private String recordStatus; 
	
	private boolean restTime; 
	
	private String comment;
	
	private boolean otOvernight;

}