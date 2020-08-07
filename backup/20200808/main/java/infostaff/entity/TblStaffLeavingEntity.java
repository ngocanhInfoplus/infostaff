package infostaff.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Entity 
@Table(name = "tblstaffleaving")
public class TblStaffLeavingEntity implements Serializable{ 
	private static final long serialVersionUID = 1L; 

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 11) 
	private Long id; 
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private TblStaffEntity tblStaffEntity;

//	@Column(name = "staff_id", nullable = false, length = 11) 
//	private Long staffId; 

//	@Column(name = "leaving_type", nullable = false, length = 10) 
//	private String leavingType; 
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leaving_type")
    private TblLeavingTypeEntity tblLeavingTypeEntity;

//	@Column(name = "timeOff_Type", nullable = false, length = 10) 
//	private String timeOffType; 
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_Off_Type")
    private TblTimeOffEntity tblTimeOffEntity;

	@Temporal(TemporalType.DATE)
	@Column(name = "from_date") 
	private Date fromDate; 

	@Temporal(TemporalType.DATE)
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
	
	@Column(name = "total_day", length = 10)
	private float totalDay;

}
