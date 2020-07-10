package infostaff.controller;

import infostaff.common.CommonFunc;
import infostaff.model.ResponseModel;
import infostaff.model.StaffTimeCardModel;
import infostaff.service.IStaffTimeCardService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static infostaff.common.CommonParam.CODE_VALIDATION_ERROR;

@Controller
@RestController
public class CheckInController {

    @Autowired
    IStaffTimeCardService service;

    @PostMapping(value = "/api/v1.0/infostaff/check-in",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel CheckIn(@RequestBody StaffTimeCardModel model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        if(!cIValid(model))
            return CommonFunc.createResponseModelByCode(CODE_VALIDATION_ERROR);
        return service.insert(model, user);
    }

    private boolean cIValid(StaffTimeCardModel model) {
        if(StringUtils.isNotBlank(model.getStaffId().toString()) || StringUtils.isNotBlank(model.getCheckIn()) || StringUtils.isNotBlank(model.getWorkingDate().toString()))
            return false;
        return true;
    }
}
