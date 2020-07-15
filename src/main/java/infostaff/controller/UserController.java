package infostaff.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infostaff.model.UserModel;

@Controller
@RestController
@RequestMapping("/api/v1.0/infostaff")
public class UserController {

	@GetMapping(value = "/user")
	public Principal user(Principal user) {
		return user;
	}

	@GetMapping(value = "/login", produces = "application/json")
	public UserModel validateLogin(Principal loginedUser) {

		return new UserModel("User successfully authenticated");
	}
}
