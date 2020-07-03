package infostaff.model; 

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString; 



@Getter 
@Setter 
@ToString 
public class DepartmentModel{ 
	private Long deparmentId; 

	private String deparmentName; 

	private String createdUser; 

	private Date createdDate; 

}