package infostaff.service;

import java.util.List;

import infostaff.model.ResponseModel;
import infostaff.model.StaffTimeCardModel;
import org.springframework.security.core.userdetails.User;

public interface IStaffTimeCardService {

    ResponseModel insert(StaffTimeCardModel model, User user);
    ResponseModel update(StaffTimeCardModel model, User user);
    List<StaffTimeCardModel> getAll();
}
