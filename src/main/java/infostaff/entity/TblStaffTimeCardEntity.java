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
@Table(name = "tblstafftimecard")
 public class TblStaffTimeCardEntity implements Serializable{ 
	private static final long serialVersionUID = 1L; 

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 11) 
	private Long id; 

	@Column(name = "staff_id", nullable = false, length = 11) 
	private Long staffId; 

	@Column(name = "working_date", nullable = false) 
	private Date workingDate; 

	@Column(name = "check_in") 
	private String checkIn; 

	@Column(name = "check_out") 
	private String checkOut; 

	@Column(name = "is_checked", length = 1) 
	private boolean isChecked; 

	@Column(name = "note", length = 100) 
	private String note; 

	@Column(name = "file_name", length = 200) 
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

}