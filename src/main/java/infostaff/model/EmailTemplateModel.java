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

	private Long defaultIdRecv01; 

	private Long defaultIdRecv02; 

	private Long defaultIdRecv03; 

	private Long defaultIdRecv04; 

	private Long defaultIdRecv05; 

	private String createdUser; 

	private Date createdDate; 

	private String recordStatus; 

}