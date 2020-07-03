package infostaff.model; 

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString; 



@Getter 
@Setter 
@ToString 
public class StaffGroupModel{ 
	private Long id; 

	private Long staffId; 

	private Long groupId; 

	private Date fromDate; 

	private Date toDate; 

	private String createdUser; 

	private Date createdDate; 

	private String recordStatus; 

}