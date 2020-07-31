package infostaff.controller;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.RoleModel;
import infostaff.model.UserModel;
import infostaff.model.mapper.UserMapper;
import infostaff.model.request.UserRequest;
import infostaff.model.response.UserResponse;
import infostaff.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1.0/infostaff")
public class UserController {

	@Autowired
	IUserService userService;

	@GetMapping(value = "/user")
	public Principal user(Principal user) {
		return user;
	}

	@GetMapping(value = "/login", produces = "application/json")
	public UserModel validateLogin(Principal loginedUser) {

		return new UserModel("User successfully authenticated");
	}
	@GetMapping(value = "use/get-user/{id}")
	public UserModel getUser(@PathVariable("id") String id, Principal principal) throws ResourceNotFoundException{

		User user = (User) ((Authentication) principal).getPrincipal();
		return userService.getUser(id, user);
	}

	@GetMapping(value = "/use/get-all")
	public List<UserModel> getAllUser(Principal principal) {

		User user = (User) ((Authentication) principal).getPrincipal();
		return userService.getAllUser(user);
	}

	@PostMapping(value = "/use/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserModel> createUser(@RequestBody UserModel model, Principal principal)
			throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(userService.insertUser(model, user));
	}

	@PutMapping("/use/update/{id}")
	public ResponseEntity<UserModel> updateUser(@PathVariable(value = "id") String id,
													@RequestBody UserModel model, Principal principal) throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(userService.updateUser(id, model, user));
	}

	@DeleteMapping(value = "/use/delete/{id}")
	public UserModel deleteUser(@PathVariable("id") String id, Principal principal) throws ResourceNotFoundException {
		User user = (User) ((Authentication) principal).getPrincipal();
		return userService.deleteUser(id, user);
	}

	@PutMapping("/use/enable/{id}")
	public ResponseEntity<UserModel> enableUser(@PathVariable(value = "id") String id,
												@RequestBody UserModel model, Principal principal) throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(userService.enableUser(id, model, user));
	}


	@PostMapping(value = "/use/createAndGrandMultiRoles", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> createUserUsingMapper(@RequestBody UserRequest model, Principal principal)
			throws ResourceNotFoundException {

		User user = (User) ((Authentication) principal).getPrincipal();
		return ResponseEntity.ok(userService.insertUserAndGrandMultiRoles(model, user));
	}
}
