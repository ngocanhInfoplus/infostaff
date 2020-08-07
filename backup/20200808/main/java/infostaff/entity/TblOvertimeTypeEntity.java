package infostaff.entity; 

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter; 


@Getter 
@Setter 
@Entity 
@Table(name = "tblovertimetype")
 public class TblOvertimeTypeEntity implements Serializable{ 
	private static final long serialVersionUID = 1L; 

	@Id 
	@Column(name = "code", nullable = false, length = 10) 
	private String code; 

	@Column(name = "name", nullable = false) 
	private String name; 

}