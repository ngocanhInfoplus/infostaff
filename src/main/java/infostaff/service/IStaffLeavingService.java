package infostaff.service;

import infostaff.exception.BadRequestException;
import infostaff.exception.ResourceNotFoundException;
import infostaff.model.StaffLeavingModel;
import infostaff.model.StaffTimeCardModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

public interface IStaffLeavingService {

    /**
     * Create leaving function
     *
     * @param model staff leaving information
     * @param user  login user
     * @return StaffLeavingModel
     * @throws ResourceNotFoundException
     */
    StaffLeavingModel CreateLeaving(StaffLeavingModel model, User user)
            throws ResourceNotFoundException, BadRequestException;
}
