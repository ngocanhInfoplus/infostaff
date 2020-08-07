package infostaff.model; 

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString; 



@Getter 
@Setter 
@ToString 
public class LeavingTypeModel {
	private String leavingCode; 

	private String leavingName; 

	private String createdUser; 

	private Date createdDate; 

	private String recordStatus; 

}