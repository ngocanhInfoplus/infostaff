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

	private String leavingType;

	private String timeOffType;

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

	// for sending email
	private List<StaffModel> staffs;
	
	private String staffName;

	// end

}