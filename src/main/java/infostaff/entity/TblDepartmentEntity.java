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
@Table(name = "tbldepartment")
 public class TblDepartmentEntity implements Serializable{ 
	private static final long serialVersionUID = 1L; 

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id", nullable = false, length = 11) 
	private Long deparmentId; 

	@Column(name = "department_name", nullable = false, length = 100) 
	private String deparmentName; 

	@Column(name = "created_user", length = 10) 
	private String createdUser; 

	@Column(name = "created_date") 
	private Date createdDate; 

}