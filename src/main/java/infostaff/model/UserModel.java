package infostaff.model; 

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString; 



@Getter 
@Setter 
@ToString 
public class UserModel{ 

	private String userName; 

	private String encrytedPassword; 

	private boolean enabled; 

	private String createdUser; 

	private Date createdDate; 
	
	private String status;
	
	private String roleName;
	
	private String token;
	
	public UserModel() {
		super();
	}
	
	public UserModel(String status) {
		this.status = status;
	}

}