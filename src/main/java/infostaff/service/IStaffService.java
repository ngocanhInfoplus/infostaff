package infostaff.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.SearchModel;
import infostaff.model.StaffModel;
import infostaff.model.UserRoleModel;

public interface IStaffService {
	
	List<StaffModel> getDirectManager(User user);
	
	List<StaffModel> getAllManager(User user);
	
	List<StaffModel> findStaffs(User user, SearchModel searchModel);
	
	StaffModel create(User user, StaffModel insertModel) throws ResourceNotFoundException;

	StaffModel update(User user, Long requestId, StaffModel model)
			throws ResourceNotFoundException;

	StaffModel cancel(User user, Long id) throws ResourceNotFoundException;
	
	UserRoleModel addRole(User user, UserRoleModel model) throws ResourceNotFoundException;

}
