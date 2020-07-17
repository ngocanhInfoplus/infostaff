package infostaff.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter 
@Setter 
@ToString 
public class DepartmentModel{ 
	private Long deparmentId; 

	private String deparmentName; 

	private String createdUser; 

	private Date createdDate; 

}