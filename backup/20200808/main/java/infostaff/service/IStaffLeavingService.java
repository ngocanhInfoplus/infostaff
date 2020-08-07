package infostaff.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.StaffAnnualLeaveModel;
import infostaff.model.StaffLeavingModel;
import infostaff.model.StaffLeavingSearchModel;

public interface IStaffLeavingService {

	List<StaffLeavingModel> getStaffLeavingByUser(User user, StaffLeavingSearchModel searchModel);

	StaffAnnualLeaveModel getStaffAnnualLeaveSummary(User user, StaffLeavingSearchModel model);

	StaffLeavingModel createRequest(User user, StaffLeavingModel insertModel) throws ResourceNotFoundException;

	StaffLeavingModel updateRequest(User user, Long requestId, StaffLeavingModel model)
			throws ResourceNotFoundException;

	StaffLeavingModel approveRequest(User user, Long requestId, StaffLeavingModel model)
			throws ResourceNotFoundException;

	StaffLeavingModel cancelRequest(User user, Long id) throws ResourceNotFoundException;

	StaffLeavingModel sendRequest(User user, Long requestId) throws ResourceNotFoundException;
	
	StaffLeavingModel createAndSendRequest(User user, StaffLeavingModel insertModel) throws ResourceNotFoundException;

}
