package infostaff.controller;

import infostaff.common.CommonFunc;
import infostaff.model.ResponseModel;
import infostaff.model.StaffTimeCardModel;
import infostaff.service.IStaffTimeCardService;
import infostaff.validation.StaffTimeCardValidation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static infostaff.common.CommonParam.CODE_VALIDATION_ERROR;


@Controller
@RestController
@RequestMapping("/api/v1.0/infostaff")
public class StaffTimeCardController {

    @Autowired
    IStaffTimeCardService service;

    StaffTimeCardValidation validation;

    @GetMapping(value = "/get-timecard")
    public List<StaffTimeCardModel> getAll() {

        return service.getAll();
    }

    @PostMapping(value = "/check-in",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel CheckIn(@RequestBody StaffTimeCardModel model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        if(!validation.cIValid(model))
            return CommonFunc.createResponseModelByCode(CODE_VALIDATION_ERROR);
        return service.insert(model, user);
    }

    @PostMapping(value = "/check-out",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel CheckOut(@RequestBody StaffTimeCardModel model, Principal principal) {

        User user = (User) ((Authentication) principal).getPrincipal();
        if(!validation.cOValid(model))
            return CommonFunc.createResponseModelByCode(CODE_VALIDATION_ERROR);
        return service.checkOut(model, user);
    }

    @PostMapping(value = "/change-timecard",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel ChangeTimeCard(@RequestBody StaffTimeCardModel model, Principal principal) {

        User user = (User) ((Authentication) principal).getPrincipal();
        if(!validation.cTCValid(model))
            return CommonFunc.createResponseModelByCode(CODE_VALIDATION_ERROR);
        return service.update(model, user);
    }


}
