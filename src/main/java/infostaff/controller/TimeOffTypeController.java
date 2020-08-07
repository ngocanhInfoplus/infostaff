package infostaff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infostaff.model.TimeOffModel;
import infostaff.service.ITimeOffService;

@RestController
@RequestMapping("/api/v1.0/infostaff/time-off")
public class TimeOffTypeController {

	@Autowired
	ITimeOffService service;
	
	@GetMapping(value="/get-all")
	public ResponseEntity<List<TimeOffModel>> getAll(){
		
		List<TimeOffModel> models = service.getAll();
		
		if(models == null || models.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(models);
	}

}
