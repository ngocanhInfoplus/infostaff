package infostaff.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.StaffTimeCardModel;

public interface IStaffTimeCardService {

	/**
	 * Check in function
	 * 
	 * @param model staff information
	 * @param user  login user
	 * @return StaffTimeCardModel
	 * @throws ResourceNotFoundException
	 */
	ResponseEntity<StaffTimeCardModel> checkIn(StaffTimeCardModel model, User user) 
			throws ResourceNotFoundException;

	/**
	 * Check out function
	 * 
	 * @param id:    id
	 * @param model: model
	 * @param user:  login user
	 * @return StaffTimeCardModel
	 * @throws ResourceNotFoundException
	 */
	ResponseEntity<StaffTimeCardModel> checkOut(Long id, StaffTimeCardModel model, User user)
			throws ResourceNotFoundException;

	/**
	 * Update function
	 * @param id:    id
	 * @param model: model
	 * @param user:  login user
	 * @return StaffTimeCardModel
	 * @throws ResourceNotFoundException
	 */
	ResponseEntity<StaffTimeCardModel> update(Long id, StaffTimeCardModel model, User user)
			throws ResourceNotFoundException;

	/**
	 * Get daily checking of login user
	 * @param loginUser
	 * @return StaffTimeCardModel
	 * @throws ResourceNotFoundException
	 */
	ResponseEntity<StaffTimeCardModel> getDailyChecking(User loginUser) 
			throws ResourceNotFoundException;
	
	//List<StaffTimeCardModel> getAll();
}
