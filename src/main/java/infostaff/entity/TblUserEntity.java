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
@Table(name = "tbluser")
 public class TblUserEntity implements Serializable{ 
	private static final long serialVersionUID = 1L; 

	@Id 
	@Column(name = "user_name", nullable = false, length = 100) 
	private String userName; 

	@Column(name = "encryted_password", nullable = false, length = 128) 
	private String encrytedPassword; 

	@Column(name = "enabled", nullable = false) 
	private boolean enabled; 

	@Column(name = "created_user", length = 10) 
	private String createdUser; 

	@Column(name = "created_date") 
	private Date createdDate; 

}