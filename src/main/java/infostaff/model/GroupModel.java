package infostaff.model; 

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString; 



@Getter 
@Setter 
@ToString 
public class GroupModel{ 
	private Long groupId; 

	private String groupName; 

	private Long departmentId; 

	private String createdUser; 

	private Date createdDate; 

}