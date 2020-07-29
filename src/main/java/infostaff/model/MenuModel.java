package infostaff.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@ToString 
public class MenuModel {
	private Long menuId; 

	private String menuName; 

	private String icon; 

	private String router; 

	private String createdUser; 

	private Date createdDate; 

	private String changedUser; 

	private Date changedDate; 

	private String recordStatus; 

	private Integer orderBy; 

	private Long parentMenuId; 

	private Long groupId; 
	
	private List<MenuModel> subMenus;
	
	private List<RoleModel> roles;
	
	private String rolesName;
}
