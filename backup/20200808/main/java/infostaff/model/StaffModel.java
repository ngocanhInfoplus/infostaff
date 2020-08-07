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
	
	private String locationName; 

	private String fileName; 

	private String createdUser; 

	private Date createdDate; 

	private String changedUser; 

	private Date changedDate; 

	private String recordStatus; 
	
	private Long managerId;
	
	private String email;
	
	private Float advanceAnnualLeave;
	
	private String jobTitleCode;
	
	private String jobTitleName;

	private String address01;

	private String address02;

	private String phoneNo;
	
	private String identityCardNo;

	private Date identityCardDate;

	private String identityCardLocation;
	
	private String countryCode;
	
	private String countryName;
	
	private String visaType;

	private String visaNo;

	private String involedName;

	private String involedPhone;
	
	private String groupCode;
	
	private String groupName;
	
	private String departmentCode;
	
	private String departmentName;
	
	private String userName;

}