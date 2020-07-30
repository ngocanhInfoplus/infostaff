package infostaff.controller;


import infostaff.exception.ResourceNotFoundException;
import infostaff.model.UserModel;
import infostaff.model.UserRoleModel;
import infostaff.service.IUserRoleService;
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
public class UserRoleController {

    @Autowired
    IUserRoleService userRoleService;

    @GetMapping(value = "/userRole/get-all")
    public List<UserRoleModel> getAllUserRole(Principal principal) {

        User user = (User) ((Authentication) principal).getPrincipal();
        return userRoleService.getAllUserRole(user);
    }

    @PostMapping(value = "/userRole/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRoleModel> createUserRole(@RequestBody UserRoleModel model, Principal principal)
            throws ResourceNotFoundException {

        User user = (User) ((Authentication) principal).getPrincipal();
        return ResponseEntity.ok(userRoleService.insertUserRole(model, user));
    }

    @PutMapping("/userRole/update/{id}")
    public ResponseEntity<UserRoleModel> updateUserRole(@PathVariable(value = "id") Long id,
                                                @RequestBody UserRoleModel model, Principal principal) throws ResourceNotFoundException {

        User user = (User) ((Authentication) principal).getPrincipal();
        return ResponseEntity.ok(userRoleService.updateUserRole(id, model, user));
    }

    @DeleteMapping(value = "/userRole/delete/{id}")
    public UserRoleModel deleteUser(@PathVariable("id") Long id, Principal principal) throws ResourceNotFoundException {
        User user = (User) ((Authentication) principal).getPrincipal();
        return userRoleService.deleteUserRole(id, user);
    }

}
