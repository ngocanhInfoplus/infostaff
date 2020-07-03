package infostaff.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;

import infostaff.model.ResponseModel;
import infostaff.model.RoleModel;

public interface IRoleService {
	
	ResponseModel insertRole(RoleModel model, User userModel);
	ResponseModel updateRole(RoleModel model, User userModel);
	
	List<RoleModel> getAllRole();
}
