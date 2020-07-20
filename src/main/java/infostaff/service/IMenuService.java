package infostaff.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.MenuModel;

public interface IMenuService {

	//List<MenuModel> getMenuByRole(Long roleId);
	
	MenuModel createMenu(User user, MenuModel menuModel) throws ResourceNotFoundException;
	
	MenuModel updateMenu(User user, MenuModel menuModel, Long updatedId) throws ResourceNotFoundException;
	
	MenuModel deleteMenu(User user, Long updatedId) throws ResourceNotFoundException;

	List<MenuModel> getMenuByRole(User user); 
	
	List<MenuModel> getAllMenu();
}
