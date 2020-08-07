package infostaff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infostaff.model.JobTitleModel;
import infostaff.service.IJobTitleService;

@RestController
@RequestMapping("/api/v1.0/infostaff/job-title")
public class JobTitlteController {
	
	@Autowired
	IJobTitleService service;
	
	@GetMapping(value="/get-all")
	public ResponseEntity<List<JobTitleModel>> getAll(){
		
		List<JobTitleModel> models = service.getAll();
		
		if(models == null || models.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(models);
	}

}
