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
@Table(name = "tblleavingtype")
public class TblLeavingTypeEntity implements Serializable{ 
	private static final long serialVersionUID = 1L; 

	@Id 
	@Column(name = "leaving_code", nullable = false, length = 10) 
	private String leavingCode; 

	@Column(name = "leaving_name", nullable = false, length = 100) 
	private String leavingName; 

	@Column(name = "created_user", length = 10) 
	private String createdUser; 

	@Column(name = "created_date") 
	private Date createdDate; 

	@Column(name = "record_status", length = 1) 
	private String recordStatus; 

}
