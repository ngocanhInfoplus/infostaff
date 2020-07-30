package infostaff.service;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.UserRoleModel;
import org.springframework.security.core.userdetails.User;
import java.util.List;

public interface IUserRoleService {

    List<UserRoleModel> getAllUserRole(User user);
    UserRoleModel getUserRole(Long id, User user) throws ResourceNotFoundException;
    UserRoleModel insertUserRole(UserRoleModel model , User user) throws ResourceNotFoundException;
    UserRoleModel updateUserRole(Long id, UserRoleModel model , User user) throws ResourceNotFoundException;
    UserRoleModel deleteUserRole(Long id, User user) throws ResourceNotFoundException;
}
