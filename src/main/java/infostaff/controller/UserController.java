package infostaff.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.UserModel;
import infostaff.service.IUserService;

@Controller
@RestController
@RequestMapping("/api/v1.0/infostaff")
public class UserController {

	@Autowired
	IUserService service;
	
	@GetMapping(value = "/user")
	public Principal user(Principal user) {
		return user;
	}

	@GetMapping(value = "/login", produces = "application/json")
	public UserModel validateLogin(Principal loginedUser) {

		return new UserModel("User successfully authenticated");
	}
	
	@GetMapping(value = "/get-user")
	public UserModel getUser(Principal principal) 
			throws ResourceNotFoundException {
		
		User user = (User) ((Authentication) principal).getPrincipal();
		return service.getUser(user);
	}
	
	@PutMapping(value = "/change-pwd/{username}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserModel> update(@PathVariable(value = "username") String username,
			@RequestBody UserModel model, Principal principal) throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(service.changePassword(user, username, model));
	}
}
