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
@Table(name = "tblstaffgroup")
 public class TblStaffGroupEntity implements Serializable{ 
	private static final long serialVersionUID = 1L; 

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 11) 
	private Long id; 

	@Column(name = "staff_id", nullable = false, length = 11) 
	private Long staffId; 

	@Column(name = "group_id", nullable = false, length = 11) 
	private Long groupId; 

	@Column(name = "from_date") 
	private Date fromDate; 

	@Column(name = "to_date") 
	private Date toDate; 

	@Column(name = "created_user", length = 10) 
	private String createdUser; 

	@Column(name = "created_date") 
	private Date createdDate; 

	@Column(name = "record_status", nullable = false, length = 1) 
	private String recordStatus; 

}