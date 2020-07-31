package infostaff.service;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.UserModel;
import infostaff.model.request.UserRequest;
import infostaff.model.response.UserResponse;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface IUserService {

    List<UserModel> getAllUser(User user);
    UserModel getUser(String id, User user) throws ResourceNotFoundException;
    UserModel insertUser(UserModel model , User user) throws ResourceNotFoundException;
    UserModel updateUser(String id, UserModel model , User user) throws ResourceNotFoundException;
    UserModel deleteUser(String id, User user) throws ResourceNotFoundException;
    UserModel enableUser(String id, UserModel model, User user) throws ResourceNotFoundException;
    UserResponse insertUserAndGrandMultiRoles(UserRequest model , User user) throws ResourceNotFoundException;
}
