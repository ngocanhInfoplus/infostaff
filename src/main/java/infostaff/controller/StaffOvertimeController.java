package infostaff.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.SearchModel;
import infostaff.model.StaffOvertimeModel;
import infostaff.service.IStaffOvertimeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1.0/infostaff/staff-ot")
public class StaffOvertimeController {
	
	@Autowired
	IStaffOvertimeService service;
	
	@PostMapping(value = "/get-by-user")
	public ResponseEntity<List<StaffOvertimeModel>> getByUser(@RequestBody SearchModel model,
			Principal principal) throws ResourceNotFoundException {

		try {

			User loginUser = (User) ((Authentication) principal).getPrincipal();
			return ResponseEntity.ok(service.getByUser(loginUser, model));

		} catch (Exception ex) {

			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException("Process failed!");
		}
	}
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StaffOvertimeModel> create(@RequestBody StaffOvertimeModel model, Principal principal)
			throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.createRequest(user, model));
	}
	
	@PostMapping(value = "/create-and-send", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StaffOvertimeModel> createAndSend(@RequestBody StaffOvertimeModel model, Principal principal)
			throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.createAndSendRequest(user, model));
	}
	
	@PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StaffOvertimeModel> update(@PathVariable(value = "id") Long id,
			@RequestBody StaffOvertimeModel model, Principal principal) throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.updateRequest(user, id, model));
	}

	@DeleteMapping(value = "/cancel/{id}")
	public ResponseEntity<StaffOvertimeModel> cancel(@PathVariable(value = "id") Long id, Principal principal)
			throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.cancelRequest(user, id));
	}

	@PutMapping(value = "/approve/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StaffOvertimeModel> approve(@PathVariable(value = "id") Long id,
			@RequestBody StaffOvertimeModel model, Principal principal) throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.approveRequest(user, id, model));
	}
	

	@DeleteMapping(value = "/send/{id}")
	public ResponseEntity<StaffOvertimeModel> send(@PathVariable(value = "id") Long id, Principal principal)
			throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.sendRequest(user, id));
	}

}
