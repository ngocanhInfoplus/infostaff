package infostaff.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infostaff.exception.ResourceNotFoundException;
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

	@GetMapping(value = "/get-")
	public ResponseEntity<List<StaffLeavingModel>> getStaffLeavingByUser(Principal principal, StaffLeavingSearchModel model)
			throws ResourceNotFoundException {

		try {
			
			User loginUser = (User) ((Authentication) principal).getPrincipal();
			return ResponseEntity.ok(service.getStaffLeavingByUser(loginUser, model));
			
		}catch(Exception ex) {
			
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException("Process failed!");
		}
	}

}
