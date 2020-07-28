package infostaff.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Entity 
@Table(name = "tblstaffleaving")
public class TblStaffLeavingEntity implements Serializable{ 
	private static final long serialVersionUID = 1L; 

	@Id 
	@Column(name = "id", nullable = false, length = 11) 
	private Long id; 

	@Column(name = "staff_id", nullable = false, length = 11) 
	private Long staffId; 

	@Column(name = "leaving_type", nullable = false, length = 10) 
	private String leavingType; 

	@Column(name = "timeOff_Type", nullable = false, length = 10) 
	private String timeOffType; 

	@Column(name = "from_date") 
	private Date fromDate; 

	@Column(name = "to_Date") 
	private Date toDate; 

	@Column(name = "reason", nullable = false, length = 100) 
	private String reason; 

	@Column(name = "manager_Id_01", nullable = false) 
	private Long managerId01; 

	@Column(name = "manager_Id_02") 
	private Long managerId02; 

	@Column(name = "comment", length = 100) 
	private String comment; 

	@Column(name = "approve_status", nullable = false, length = 10) 
	private String approveStatus; 

	@Column(name = "file_name", length = 100) 
	private String fileName; 

	@Column(name = "created_user", length = 10) 
	private String createdUser; 

	@Column(name = "created_date") 
	private Date createdDate; 

	@Column(name = "changed_user", length = 10) 
	private String changedUser; 

	@Column(name = "changed_date") 
	private Date changedDate; 

	@Column(name = "approved_user", length = 10) 
	private String approvedUser; 

	@Column(name = "approved_date") 
	private Date approvedDate; 

	@Column(name = "record_status", length = 1) 
	private String recordStatus;

	@Column(name = "total_hour", length = 2)
	private int totalHour;

}
