package infostaff.controller;

import infostaff.model.StaffTimeCardModel;
import infostaff.service.IStaffTimeCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
public class GetTimeCardController {
    @Autowired
    IStaffTimeCardService service;

    @PostMapping(value = "/api/v1.0/infostaff/get-timecard",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffTimeCardModel> getAll() {

        return service.getAll();
    }
}
