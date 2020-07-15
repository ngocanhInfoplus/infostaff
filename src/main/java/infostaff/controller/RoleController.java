package infostaff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.RoleModel;
import infostaff.service.IRoleService;

@Controller
@RestController
@RequestMapping("/api/v1.0/infostaff/role")
public class RoleController {

	@Autowired
	IRoleService roleService;

	@GetMapping(value = "/get-all")
	public List<RoleModel> getAllRoles() {

		return roleService.getAllRole();
	}

	@GetMapping(value = "/get-role/{id}")
	public RoleModel getRole(@PathVariable("id") Long roleId) {

		return roleService.getRole(roleId);
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RoleModel> createRole(@RequestBody RoleModel model) 
			throws ResourceNotFoundException {

		return roleService.insertRole(model);
	}

	@DeleteMapping(value = "/delete/{id}")
	public RoleModel deleteRole(@PathVariable("id") Long roleId) throws ResourceNotFoundException {
		return roleService.deleteRole(roleId);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<RoleModel> updateEmployee(@PathVariable(value = "id") Long roleId,
			@RequestBody RoleModel roleModel) throws ResourceNotFoundException {

		return roleService.updateRole(roleId, roleModel);
	}

//	@PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseModel createRole(@RequestBody RoleModel model, Principal principal) {
//		
//		User loginedUser = (User) ((Authentication) principal).getPrincipal();
//		return roleService.insertRole(model, loginedUser);
//	}
//	
//	@PostMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseModel updateRole(@RequestBody RoleModel model, Principal principal) {
//		
//		User loginedUser = (User) ((Authentication) principal).getPrincipal();
//		return roleService.updateRole(model, loginedUser);
//	}
}
