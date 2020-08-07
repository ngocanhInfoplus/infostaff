package infostaff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infostaff.model.OvertimeTypeModel;
import infostaff.service.IOvertimeTypeSevice;

@RestController
@RequestMapping("/api/v1.0/infostaff/ot")
public class OvertimeTypeController {
	
	@Autowired
	IOvertimeTypeSevice service;
	
	@GetMapping(value="/get-all")
	public ResponseEntity<List<OvertimeTypeModel>> getAll(){
		
		List<OvertimeTypeModel> models = service.getAll();
		
		if(models == null || models.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(models);
	}


}
