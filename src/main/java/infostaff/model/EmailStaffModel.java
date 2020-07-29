package infostaff.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailStaffModel {
	
	private Long id;
	
	private String emailTempCode;
	
	private Long staffId;
	
	private String createdUser;
	
	private Date createdDate;
	
	private String recordStatus;
}
