package infostaff.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.MenuModel;
import infostaff.service.IMenuService;

@RestController
@RequestMapping("/api/v1.0/infostaff/menu")
public class MenuController {
	
	@Autowired
	IMenuService menuService;
	
	@GetMapping(value="/show")
	public ResponseEntity<List<MenuModel>> showMenu(Principal principal){
		
		User user = (User) ((Authentication) principal).getPrincipal();
		List<MenuModel> menus = menuService.getMenuByRole(user);
		
		if(menus == null || menus.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(menus);
	}
	
	@GetMapping(value="/get-all")
	public ResponseEntity<List<MenuModel>> getAll(){
		
		List<MenuModel> menus = menuService.getAllMenu();
		
		if(menus == null || menus.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(menus);
	}
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MenuModel> createRole(@RequestBody MenuModel model, Principal principal) 
			throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(menuService.createMenu(user, model));
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<MenuModel> deleteRole(@PathVariable("id") Long id , Principal principal) 
			throws ResourceNotFoundException {
		
		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(menuService.deleteMenu(user, id));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<MenuModel> updateEmployee(@PathVariable(value = "id") Long id,
			@RequestBody MenuModel model, Principal principal) throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(menuService.updateMenu(user, model, id));
	}

}
