package infostaff.model; 

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;


@Getter 
@Setter 
@ToString 
public class UserModel{

	@NotBlank
	private String userName;
	@NotBlank
	private String encrytedPassword;
	@NotBlank
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