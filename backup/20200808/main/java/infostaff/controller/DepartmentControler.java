package infostaff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infostaff.model.DepartmentModel;
import infostaff.model.GroupModel;
import infostaff.service.IDepartmentService;
import infostaff.service.IGroupService;

@RestController
@RequestMapping("/api/v1.0/infostaff/department")
public class DepartmentControler {
	
	@Autowired
	IDepartmentService deparmentService;
	
	@Autowired
	IGroupService groupService;
	
	@GetMapping(value="/get-all")
	public ResponseEntity<List<DepartmentModel>> getAll(){
		
		List<DepartmentModel> models = deparmentService.getAll();
		
		if(models == null || models.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(models);
	}
	
	@GetMapping(value="/get-all-group/{code}")
	public ResponseEntity<List<GroupModel>> getAllGroups(@PathVariable(value = "code") String code){
		
		List<GroupModel> models = groupService.getAllByDepartment(code);
		
		if(models == null || models.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(models);
	}

}
