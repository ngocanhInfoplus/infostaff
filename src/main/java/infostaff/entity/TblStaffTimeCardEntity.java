package infostaff.entity; 

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter 
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity 
@Table(name = "tblstafftimecard")
 public class TblStaffTimeCardEntity implements Serializable{ 
	private static final long serialVersionUID = 1L; 

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 11) 
	private Long id; 

//	@Column(name = "staff_id", nullable = false, length = 11) 
//	private Long staffId; 
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private TblStaffEntity tblStaffEntity;

	@Temporal(TemporalType.DATE)
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

	@CreatedBy
	@Column(name = "created_user", length = 10) 
	private String createdUser; 

	@CreatedDate
	@Column(name = "created_date") 
	private Date createdDate; 

	@LastModifiedBy
	@Column(name = "changed_user", length = 10) 
	private String changedUser; 

	@LastModifiedDate
	@Column(name = "changed_date") 
	private Date changedDate; 

	@Column(name = "record_status", length = 1) 
	private String recordStatus; 

}