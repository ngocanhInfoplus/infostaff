package infostaff.service;

import java.util.List;

import infostaff.model.GroupModel;

public interface IGroupService {
	
	List<GroupModel> getAll();
	
	List<GroupModel> getAllByDepartment(String departmentCode);
}
