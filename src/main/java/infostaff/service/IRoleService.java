package infostaff.service;

import java.util.List;

import infostaff.model.ResponseModel;
import infostaff.model.RoleModel;

public interface IRoleService {
	
	ResponseModel insertRole(RoleModel model);
	
	ResponseModel updateRole(RoleModel model);
	
	List<RoleModel> getAllRole();
}
