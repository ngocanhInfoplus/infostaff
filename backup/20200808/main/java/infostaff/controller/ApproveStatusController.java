package infostaff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infostaff.model.ApproveStatusModel;
import infostaff.service.IApproveStatusService;

@RestController
@RequestMapping("/api/v1.0/infostaff/approve-status")
public class ApproveStatusController {
	
	@Autowired
	IApproveStatusService service;
	
	@GetMapping(value="/get-all")
	public ResponseEntity<List<ApproveStatusModel>> getAll(){
		
		List<ApproveStatusModel> models = service.getAll();
		
		if(models == null || models.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(models);
	}
}
