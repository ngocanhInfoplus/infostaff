package infostaff.service;

import org.springframework.security.core.userdetails.User;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.UserModel;

public interface IUserService {
	
	UserModel create(User user, UserModel model) throws ResourceNotFoundException;
	
	UserModel update(User user, String username, UserModel model) throws ResourceNotFoundException;
	
	UserModel changePassword(User user, String username, UserModel model) throws ResourceNotFoundException;
	
	UserModel disableUser(User user, String username) throws ResourceNotFoundException;
	
	UserModel getUser(User user) throws ResourceNotFoundException;

}
