package infostaff.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.StaffTimeCardModel;
import infostaff.service.IStaffTimeCardService;

@RestController
@RequestMapping("/api/v1.0/infostaff")
public class StaffTimeCardController {

	@Autowired
	IStaffTimeCardService service;

//	@GetMapping(value = "/get-all")
//	public List<StaffTimeCardModel> getAll() {
//
//		return service.getAll();
//	}

	@PostMapping(value = "/check-in", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StaffTimeCardModel> checkIn(@RequestBody StaffTimeCardModel model, Principal principal)
			throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return service.checkIn(model, user);
	}

	@PutMapping(value = "/check-out/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StaffTimeCardModel> checkOut(@PathVariable(value = "id") Long id,
			@RequestBody StaffTimeCardModel model, Principal principal) throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return service.checkOut(id, model, user);
	}

	@PostMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StaffTimeCardModel> update(@PathVariable(value = "id") Long id,
			@RequestBody StaffTimeCardModel model, Principal principal) throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return service.update(id, model, user);
	}

	@GetMapping(value = "/get-daily-checked")
	public ResponseEntity<StaffTimeCardModel> getDailyChecking(Principal principal) throws ResourceNotFoundException {

		User loginUser = (User) ((Authentication) principal).getPrincipal();
		return service.getDailyChecking(loginUser);
	}
	
	@PostMapping(value = "/time-card/get-data", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StaffTimeCardModel>> getStaffTimeCard(Principal principal, @RequestBody StaffTimeCardModel searchModel) 
			throws ResourceNotFoundException {

		User loginUser = (User) ((Authentication) principal).getPrincipal();
		List<StaffTimeCardModel> models = service.getStaffTimeCard(loginUser, searchModel);
		
		if(models == null || models.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(models);
	}

}
