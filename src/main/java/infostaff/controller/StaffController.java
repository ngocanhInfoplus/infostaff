package infostaff.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.SearchModel;
import infostaff.model.StaffModel;
import infostaff.model.UserRoleModel;
import infostaff.service.IStaffService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1.0/infostaff/staff")
public class StaffController {
	
	@Autowired
	IStaffService service;
	
	@GetMapping(value="/get-direct-manager")
	public ResponseEntity<List<StaffModel>> getDirectManager(Principal principal){

		User user = (User) ((Authentication) principal).getPrincipal();
		List<StaffModel> model = service.getDirectManager(user);
		
		if(model == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(model);
		
	}
	
	@GetMapping(value="/get-all-manager")
	public ResponseEntity<List<StaffModel>> getAllManager(Principal principal){

		User user = (User) ((Authentication) principal).getPrincipal();
		List<StaffModel> models = service.getAllManager(user);
		
		if(models == null || models.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(models);
		
	}
	
	@PostMapping(value="/get-staffs")
	public ResponseEntity<List<StaffModel>> getStaff(@RequestBody SearchModel model,
			Principal principal) throws ResourceNotFoundException {

		try {

			User loginUser = (User) ((Authentication) principal).getPrincipal();
			return ResponseEntity.ok(service.findStaffs(loginUser, model));

		} catch (Exception ex) {

			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException("Process failed!");
		}
	}
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StaffModel> create(@RequestBody StaffModel model, Principal principal)
			throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.create(user, model));
	}

	@PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StaffModel> update(@PathVariable(value = "id") Long id,
			@RequestBody StaffModel model, Principal principal) throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.update(user, id, model));
	}

	@DeleteMapping(value = "/cancel/{id}")
	public ResponseEntity<StaffModel> cancel(@PathVariable(value = "id") Long id, Principal principal)
			throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.cancel(user, id));
	}
	
	@PutMapping(value = "/add-role", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserRoleModel> addRole(@RequestBody UserRoleModel model
			, Principal principal) throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.addRole(user, model));
	}
}
