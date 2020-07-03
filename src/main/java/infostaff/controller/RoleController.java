package infostaff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infostaff.model.ResponseModel;
import infostaff.model.RoleModel;
import infostaff.service.IRoleService;

@Controller
@RestController
@RequestMapping("/api/v1.0/infostaff/role")
public class RoleController {

	@Autowired
	IRoleService roleService;

	@PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseModel createRole(@RequestBody RoleModel model) {
		
		return roleService.insertRole(model);
	}
	
	@PostMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseModel updateRole(@RequestBody RoleModel model) {
		
		return roleService.updateRole(model);
	}

	@GetMapping(value = "/get-all")
	public List<RoleModel> getAllRoles() {

		return roleService.getAllRole();
	}
}
