package infostaff.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.SearchModel;
import infostaff.model.StaffOvertimeModel;

public interface IStaffOvertimeService {

	List<StaffOvertimeModel> getByUser(User user, SearchModel searchModel) throws ResourceNotFoundException;

	StaffOvertimeModel createRequest(User user, StaffOvertimeModel insertModel) throws ResourceNotFoundException;

	StaffOvertimeModel updateRequest(User user, Long requestId, StaffOvertimeModel model)
			throws ResourceNotFoundException;
	
	StaffOvertimeModel approveRequest(User user, Long requestId, StaffOvertimeModel model)
			throws ResourceNotFoundException;

	StaffOvertimeModel cancelRequest(User user, Long id) throws ResourceNotFoundException;

	StaffOvertimeModel sendRequest(User user, Long requestId) throws ResourceNotFoundException;
	
	StaffOvertimeModel createAndSendRequest(User user, StaffOvertimeModel model) throws ResourceNotFoundException;


}
