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
import infostaff.model.StaffAnnualLeaveModel;
import infostaff.model.StaffLeavingModel;
import infostaff.model.StaffLeavingSearchModel;
import infostaff.service.IStaffLeavingService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1.0/infostaff/staff-leave")
public class StaffLeavingController {

	@Autowired
	IStaffLeavingService service;

	@PostMapping(value = "/get-by-user")
	public ResponseEntity<List<StaffLeavingModel>> getStaffLeavingByUser(@RequestBody StaffLeavingSearchModel model,
			Principal principal) throws ResourceNotFoundException {

		try {

			User loginUser = (User) ((Authentication) principal).getPrincipal();
			return ResponseEntity.ok(service.getStaffLeavingByUser(loginUser, model));

		} catch (Exception ex) {

			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException("Process failed!");
		}
	}

	@PostMapping(value = "/get-annual-leave-summary")
	public ResponseEntity<StaffAnnualLeaveModel> getStaffAnnualLeaveSummary(@RequestBody StaffLeavingSearchModel model,
			Principal principal) throws ResourceNotFoundException {

		try {

			User loginUser = (User) ((Authentication) principal).getPrincipal();
			return ResponseEntity.ok(service.getStaffAnnualLeaveSummary(loginUser, model));

		} catch (Exception ex) {

			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException("Process failed!");
		}
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StaffLeavingModel> create(@RequestBody StaffLeavingModel model, Principal principal)
			throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.createRequest(user, model));
	}

	@PostMapping(value = "/create-and-send", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StaffLeavingModel> createAndSend(@RequestBody StaffLeavingModel model, Principal principal)
			throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.createAndSendRequest(user, model));
	}

	@PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StaffLeavingModel> update(@PathVariable(value = "id") Long id,
			@RequestBody StaffLeavingModel model, Principal principal) throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.updateRequest(user, id, model));
	}

	@DeleteMapping(value = "/cancel/{id}")
	public ResponseEntity<StaffLeavingModel> cancel(@PathVariable(value = "id") Long id, Principal principal)
			throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.cancelRequest(user, id));
	}

	@PutMapping(value = "/approve/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StaffLeavingModel> approve(@PathVariable(value = "id") Long id,
			@RequestBody StaffLeavingModel model, Principal principal) throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.approveRequest(user, id, model));
	}

//	@GetMapping(value = "/send/{id}")
//	public ResponseEntity<StaffLeavingModel> send(@PathVariable(value = "id") Long id, Principal principal)
//			throws ResourceNotFoundException {
//
//		User user = (User) ((Authentication) principal).getPrincipal();
//		return ResponseEntity.ok(service.sendRequest(user, id));
//	}
	
	@DeleteMapping(value = "/send/{id}")
	public ResponseEntity<StaffLeavingModel> send(@PathVariable(value = "id") Long id, Principal principal)
			throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.sendRequest(user, id));
	}

}
