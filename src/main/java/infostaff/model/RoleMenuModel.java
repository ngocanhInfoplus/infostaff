package infostaff.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@ToString 
public class RoleMenuModel {

	private Long id; 

	private Long roleId; 

	private Long menuId; 

	private String createdUser; 

	private Date createdDate; 

	private String changedUser; 

	private Date changedDate; 

	private String recordStatus; 
}
