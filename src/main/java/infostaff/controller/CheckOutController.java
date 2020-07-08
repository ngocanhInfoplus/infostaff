package infostaff.controller;

import infostaff.model.ResponseModel;
import infostaff.model.StaffTimeCardModel;
import infostaff.service.IStaffTimeCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
@RestController
public class CheckOutController {
    @Autowired
    IStaffTimeCardService service;

    @PostMapping(value = "/api/v1.0/infostaff/check-out",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel createRole(@RequestBody StaffTimeCardModel model, Principal principal) {

        User user = (User) ((Authentication) principal).getPrincipal();
        return service.update(model, user);
    }
}
