package infostaff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infostaff.model.LeavingTypeModel;
import infostaff.service.ILeavingTypeService;

@RestController
@RequestMapping("/api/v1.0/infostaff/leaving-type")
public class LeavingTypeController {
	
	@Autowired
	ILeavingTypeService leavingTypeService;
	
	@GetMapping(value="/get-all")
	public ResponseEntity<List<LeavingTypeModel>> getAll(){
		
		List<LeavingTypeModel> models = leavingTypeService.getAll();
		
		if(models == null || models.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(models);
	}

}
