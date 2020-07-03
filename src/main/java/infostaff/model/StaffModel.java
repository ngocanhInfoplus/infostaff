package infostaff.model; 

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString; 



@Getter 
@Setter 
@ToString 
public class StaffModel{ 
	private Long staffId; 

	private String staffCode; 

	private String staffName; 

	private Date joinDate; 

	private Date dob; 

	private String gender; 

	private String locationCode; 

	private String fileName; 

	private String createdUser; 

	private Date createdDate; 

	private String changedUser; 

	private Date changedDate; 

	private String recordStatus; 

}