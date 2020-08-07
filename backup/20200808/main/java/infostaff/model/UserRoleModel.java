package infostaff.model; 

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString; 



@Getter 
@Setter 
@ToString 
public class UserRoleModel{ 
	private Long id; 

	private String userName; 

	private Long roleId; 
	
	private String createdUser; 

	private Date createdDate; 

}