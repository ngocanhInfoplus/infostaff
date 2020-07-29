package infostaff.entity; 

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter; 


@Getter 
@Setter 
@Entity 
@Table(name = "tblstaff")
 public class TblStaffEntity implements Serializable{ 
	private static final long serialVersionUID = 1L; 

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id", nullable = false, length = 11) 
	private Long staffId; 

	@Column(name = "staff_code", nullable = false, length = 20) 
	private String staffCode; 

	@Column(name = "staff_name", nullable = false, length = 100) 
	private String staffName; 

	@Column(name = "join_date", nullable = false) 
	private Date joinDate; 

	@Column(name = "dob", nullable = false) 
	private Date dob; 

	@Column(name = "gender", nullable = false, length = 1) 
	private String gender; 

	@Column(name = "location_code", nullable = false, length = 10) 
	private String locationCode; 

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

	@Column(name = "record_status", length = 1) 
	private String recordStatus; 
	
	@Column(name = "manager_id") 
	private Long managerId; 
	
	@Column(name = "email", length = 100) 
	private String email; 

}