package infostaff.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tblgroup")
public class TblGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "group_code", nullable = false, length = 10)
	private String groupCode;

	@Column(name = "group_name", nullable = false, length = 100)
	private String groupName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_code")
	private TblDepartmentEntity departmentEntity;

//	@Column(name = "department_code", nullable = false, length = 10) 
//	private String departmentCode; 
}