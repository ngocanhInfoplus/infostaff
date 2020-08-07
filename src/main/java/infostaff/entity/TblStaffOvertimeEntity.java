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
@Table(name = "tblstaffovertime")
 public class TblStaffOvertimeEntity implements Serializable{ 
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

	@Temporal(TemporalType.DATE)
	@Column(name = "register_date", nullable = false) 
	private Date registerDate; 

	@Column(name = "from_time", nullable = false) 
	private Date fromTime; 

	@Column(name = "to_time") 
	private Date toTime; 

	@Column(name = "project_name", length = 100) 
	private String projectName; 

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ot_type")
	private TblOvertimeTypeEntity tblOvertimeTypeEntity;
//	@Column(name = "ot_type", nullable = false, length = 10) 
//	private String otType;

	@Column(name = "approve_status", length = 10) 
	private String approveStatus; 

	@Column(name = "manager_id_01", length = 10) 
	private Long managerId01; 

	@Column(name = "manager_id_02", length = 10) 
	private Long managerId02;
	
	@Column(name = "total_hour", length = 11) 
	private Float totalHour;

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
	
	@Column(name = "ot_overnight", length = 1) 
	private boolean otOvernight; 
	
	@Column(name = "rest_time", length = 1) 
	private boolean restTime; 
	
	@Column(name = "comment", length = 100)
	private String comment;
}