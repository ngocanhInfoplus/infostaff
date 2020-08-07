package infostaff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infostaff.model.CountryModel;
import infostaff.service.ICountryService;

@RestController
@RequestMapping("/api/v1.0/infostaff/country")
public class CountryControler {
	
	@Autowired
	ICountryService service;
	
	@GetMapping(value="/get-all")
	public ResponseEntity<List<CountryModel>> getAll(){
		
		List<CountryModel> models = service.getAll();
		
		if(models == null || models.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(models);
	}

}
