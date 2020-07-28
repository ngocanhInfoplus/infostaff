package infostaff.service;

import infostaff.exception.BadRequestException;
import infostaff.exception.ResourceNotFoundException;
import infostaff.model.StaffLeavingModel;
import infostaff.model.request.StaffLeavingRequest;
import infostaff.model.response.StaffLeavingResponse;
import org.springframework.security.core.userdetails.User;

public interface IStaffLeavingService {

    StaffLeavingResponse createLeaving(StaffLeavingRequest request, User user) throws BadRequestException;
}
