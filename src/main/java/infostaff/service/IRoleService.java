package infostaff.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.RoleModel;
import infostaff.model.UserRoleModel;

public interface IRoleService {
	
//	ResponseModel insertRole(RoleModel model, User userModel);
//	ResponseModel updateRole(RoleModel model, User userModel);

	List<RoleModel> getAllRole();
	RoleModel getRole(Long id);
	
	UserRoleModel getRoleByUser(String username);
	
	ResponseEntity<RoleModel> insertRole(RoleModel model) throws ResourceNotFoundException;
	ResponseEntity<RoleModel> updateRole(Long roleId, RoleModel model) throws ResourceNotFoundException;
	RoleModel deleteRole(Long roleId) throws ResourceNotFoundException;

}
