package infostaff.controller;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.StaffLeavingModel;
import infostaff.model.StaffTimeCardModel;
import infostaff.service.IStaffLeavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1.0/infostaff")
public class StaffLeavingController {

    @Autowired
    IStaffLeavingService service;

    @PostMapping(value = "/create-leaving-request", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffLeavingModel> CreateLeaving(@RequestBody StaffLeavingModel model, Principal principal)
            throws ResourceNotFoundException {

        User user = (User) ((Authentication) principal).getPrincipal();
        return ResponseEntity.ok(service.CreateLeaving(model, user));
    }
}
