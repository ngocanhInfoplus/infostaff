package infostaff.entity; 

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter 
@Setter
@EntityListeners(AuditingEntityListener.class)
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

	@CreatedBy
	@Column(name = "created_user", length = 10) 
	private String createdUser; 

	@CreatedDate
	@Column(name = "created_date") 
	private Date createdDate; 

	@Column(name = "record_status", nullable = false, length = 1) 
	private String recordStatus; 

}