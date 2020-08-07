package infostaff.model; 

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString; 



@Getter 
@Setter 
@ToString 
public class DepartmentModel{ 
	private String departmentCode; 

	private String departmentName; 
	
	private List<GroupModel> groups;

}