package infostaff.model; 

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString; 



@Getter 
@Setter 
@ToString 
public class UserModel{ 
	private Long userId; 

	private String userName; 

	private String encrytedPassword; 

	private boolean enabled; 

	private String createdUser; 

	private Date createdDate; 

}