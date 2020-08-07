package infostaff.model; 

import lombok.Getter;
import lombok.Setter;
import lombok.ToString; 



@Getter 
@Setter 
@ToString 
public class GroupModel{ 
	private String groupCode; 

	private String groupName; 

	private String departmentCode; 
	
	private String departmentName;

}