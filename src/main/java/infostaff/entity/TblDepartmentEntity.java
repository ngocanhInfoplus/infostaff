package infostaff.entity; 

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@Column(name = "department_code", nullable = false) 
	private String departmentCode; 

	@Column(name = "department_name", nullable = false, length = 100) 
	private String departmentName; 
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departmentEntity")
    private List<TblGroupEntity> groups;
 

}