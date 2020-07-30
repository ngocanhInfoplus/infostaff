package infostaff.model; 

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;


@Getter 
@Setter 
@ToString 
public class UserRoleModel{
	@NotBlank
	private Long id;
	@NotBlank
	private String userName;
	@NotBlank
	private Long roleId; 
	
	private String createdUser; 

	private Date createdDate; 

}