package infostaff.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StaffLeavingModel {
	private Long id;

	private Long staffId;

	private String leavingTypeCode;

	private String timeOffCode;

	private Date fromDate;

	private Date toDate;

	private String reason;

	private Long managerId01;

	private Long managerId02;

	private String comment;

	private String approveStatus;

	private String fileName;

	private String createdUser;

	private Date createdDate;

	private String changedUser;

	private Date changedDate;

	private String approvedUser;

	private Date approvedDate;

	private String recordStatus;
	
	private Float advanceAnualLeave;
	
	private String approveStatusName;
	
	private Float totalDay;

	// for search
	private String staffName;
	
	private String leavingTypeName;

	private String timeOffName;
	
	private String managerName01;
	
	private String managerName02;
	// end search
	
	// for sending email
	private List<StaffModel> staffs;
	// end
}