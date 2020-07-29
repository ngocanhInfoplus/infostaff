package infostaff.model; 

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString; 



@Getter 
@Setter 
@ToString 
public class EmailTemplateModel {
	private String templateCode; 

	private String templateContent; 
	
	private String templateSubject;

	private String createdUser; 

	private Date createdDate; 

	private String recordStatus; 

}