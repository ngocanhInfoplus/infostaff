package infostaff.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import infostaff.common.CommonFunc;
import infostaff.common.CommonParam;
import infostaff.entity.TblEmailTemplateEntity;
import infostaff.entity.TblStaffEntity;
import infostaff.entity.TblStaffLeavingEntity;
import infostaff.exception.ResourceNotFoundException;
import infostaff.mapping.EmailTemplateMapping;
import infostaff.mapping.StaffLeavingMapping;
import infostaff.mapping.StaffMapping;
import infostaff.model.EmailTemplateModel;
import infostaff.model.StaffLeavingModel;
import infostaff.model.StaffLeavingSearchModel;
import infostaff.model.StaffModel;
import infostaff.repository.TblEmailStaffRepository;
import infostaff.repository.TblEmailTemplateRepository;
import infostaff.repository.TblStaffLeavingRepository;
import infostaff.repository.TblStaffRepository;
import infostaff.validation.StaffLeavingValidation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StaffLeavingServiceImpl implements IStaffLeavingService {

	@Autowired
	TblStaffRepository staffRepo;

	@Autowired
	TblEmailStaffRepository emailStaffRepo;

	@Autowired
	TblStaffLeavingRepository staffLeavingRepo;

	@Autowired
	TblEmailTemplateRepository emailTemplateRepo;
	
	@Autowired
	IEmailService emailService;

	final String TEMPLATE_LEAVE_REQ = "LEAVE_REQ";
	final String TEMPLATE_LEAVE_RES = "LEAVE_RES";

	@Override
	public List<StaffLeavingModel> getStaffLeavingByUser(User user, StaffLeavingSearchModel searchModel) {

		// get role
		String roleName = CommonFunc.getRoleName(user);

		if (StringUtils.isEmpty(roleName))
			return null;

		switch (roleName) {
		case CommonParam.ROLE_USER:
			return searchByStaffUser(user, searchModel);

		case CommonParam.ROLE_MANAGER:
			return searchByManagerUser(user, searchModel);
		}

		return null;
	}

	private List<StaffLeavingModel> searchByStaffUser(User user, StaffLeavingSearchModel searchModel) {

		TblStaffEntity staffEntity = staffRepo.findActivedStaff(user.getUsername(), CommonParam.RC_OPEN);

		if (staffEntity == null)
			return null;

		List<TblStaffLeavingEntity> entities = staffLeavingRepo.findActiveRequestByStaffIdAndDate(
				staffEntity.getStaffId(), searchModel.getFromDate(), searchModel.getToDate(), CommonParam.RC_OPEN);

		if (entities == null || entities.isEmpty())
			return null;

		StaffLeavingMapping mapping = new StaffLeavingMapping();
		List<StaffLeavingModel> models = new ArrayList<StaffLeavingModel>();

		for (TblStaffLeavingEntity entity : entities) {
			models.add(mapping.entityToModel(entity));
		}

		return models;

	}

	private List<StaffLeavingModel> searchByManagerUser(User user, StaffLeavingSearchModel searchModel) {

		List<TblStaffLeavingEntity> entities = staffLeavingRepo.findActiveRequestByDate(searchModel.getFromDate(),
				searchModel.getToDate(), CommonParam.RC_OPEN);

		if (entities == null || entities.isEmpty())
			return null;

		StaffLeavingMapping mapping = new StaffLeavingMapping();
		List<StaffLeavingModel> models = new ArrayList<StaffLeavingModel>();

		for (TblStaffLeavingEntity entity : entities) {
			models.add(mapping.entityToModel(entity));
		}

		return models;

	}

	@Override
	public StaffLeavingModel createRequest(User user, StaffLeavingModel insertModel) throws ResourceNotFoundException {

		StaffLeavingValidation validation = new StaffLeavingValidation();
		StaffLeavingMapping mapping = new StaffLeavingMapping();

		if (!validation.validate(insertModel))
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// parsing data
		TblStaffLeavingEntity entity = mapping.modelToEntity(insertModel);

		if (entity == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);

		entity.setTotalHour(getTotalHours(insertModel));
		entity.setRecordStatus(CommonParam.RC_OPEN);
		entity.setCreatedUser(user.getUsername());
		entity.setCreatedDate(new Date());

		try {
			// insert data to db
			final StaffLeavingModel insertedModel = mapping.entityToModel(staffLeavingRepo.save(entity));
			return insertedModel;
		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	@Override
	public StaffLeavingModel updateRequest(User user, Long updateId, StaffLeavingModel newModel)
			throws ResourceNotFoundException {

		StaffLeavingValidation validation = new StaffLeavingValidation();
		StaffLeavingMapping mapping = new StaffLeavingMapping();

		if (updateId == null || !validation.validate(newModel))
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// parsing data
		TblStaffLeavingEntity newEntity = mapping.modelToEntity(newModel);

		if (newEntity == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);

		try {
			// find old data
			TblStaffLeavingEntity oldEntity = staffLeavingRepo.findById(updateId).orElseThrow(
					() -> new ResourceNotFoundException("The leaving request not found for this id :: " + updateId));

			if (!"I".equals(oldEntity.getApproveStatus()))
				throw new ResourceNotFoundException("The leaving request is send to approve");

			// setup update
			newEntity.setId(updateId);
			newEntity.setChangedUser(user.getUsername());
			newEntity.setChangedDate(new Date());

			final StaffLeavingModel updatedModel = mapping.entityToModel(staffLeavingRepo.save(newEntity));
			return updatedModel;

		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	@Override
	public StaffLeavingModel approveRequest(User user, Long requestId, StaffLeavingModel model)
			throws ResourceNotFoundException {

		StaffLeavingMapping mapping = new StaffLeavingMapping();

		if (requestId == null || model == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// parsing data
		TblStaffLeavingEntity newEntity = mapping.modelToEntity(model);

		if (newEntity == null || StringUtils.isEmpty(newEntity.getApproveStatus()))
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);

		try {
			// find old data
			TblStaffLeavingEntity oldEntity = staffLeavingRepo.findById(requestId).orElseThrow(
					() -> new ResourceNotFoundException("The leaving request not found for this id :: " + requestId));

			if ("A".equals(oldEntity.getApproveStatus()) || "D".equals(oldEntity.getApproveStatus()))
				throw new ResourceNotFoundException("The leaving request was approved/deny");

			// setup update
			newEntity.setId(requestId);
			newEntity.setApprovedUser(user.getUsername());
			newEntity.setApprovedDate(new Date());

			final StaffLeavingModel updatedModel = mapping.entityToModel(staffLeavingRepo.save(newEntity));
			return updatedModel;

		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	private int getTotalHours(StaffLeavingModel model) {

		switch (model.getTimeOffType()) {
		case CommonParam.TIMEOFF_HALF:
			return 4;
		case CommonParam.TIMEOFF_FULL:
			return 8;
		case CommonParam.TIMEOFF_OTHER:
			return CommonFunc.hoursDifference(model.getToDate(), model.getFromDate());
		}
		return 0;
	}

	@Override
	public StaffLeavingModel sendRequest(User user, StaffLeavingSearchModel searchModel) {
		// TODO Auto-generated method stub
		return null;
	}

	private String sendStaffLeavingEmail(StaffLeavingModel model, String templateCode) {

		if (model == null || StringUtils.isEmpty(templateCode))
			return "Dont have information to send!";

		// get manager info
		Long managerId = (model.getManagerId02() == null) ? model.getManagerId01() : model.getManagerId02();
		TblStaffEntity managerStaffEntity = staffRepo.findById(managerId).orElse(null);

		// get group staffs info
		String ccTo = getEmailString(model.getStaffs());

		// get default staffs info
		List<StaffModel> defaultStaffs = getDefaultStaffs(templateCode);

		if (StringUtils.isNotEmpty(ccTo))
			ccTo += "," + getEmailString(defaultStaffs);
		else
			ccTo = getEmailString(defaultStaffs);

		TblEmailTemplateEntity emailTemplateEntity = emailTemplateRepo.findByTemplateCode(templateCode);

		EmailTemplateMapping mapping = new EmailTemplateMapping();
		EmailTemplateModel emailTemplateModel = mapping.entityToModel(emailTemplateEntity);
		String parsingContent = parsingConTent(templateCode, model, emailTemplateModel);

		emailService.sendSimpleHtmlMessage(managerStaffEntity.getEmail(), ccTo,
				emailTemplateModel.getTemplateSubject(), parsingContent);
		
		return "Send success";

	}

	private String getEmailString(List<StaffModel> entities) {

		if (entities == null || entities.isEmpty())
			return StringUtils.EMPTY;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < entities.size(); i++) {

			if (i == entities.size() - 1)
				sb.append(entities.get(i).getEmail());
			else
				sb.append(entities.get(i).getEmail() + ",");
		}
		return sb.toString();
	}

	private List<StaffModel> getDefaultStaffs(String templateCode) {

		List<TblStaffEntity> entities = emailStaffRepo.findStaffByEmailTemplateCode(templateCode, CommonParam.RC_OPEN);

		if (entities == null || entities.isEmpty())
			return null;

		List<StaffModel> models = new ArrayList<StaffModel>();
		StaffMapping mapping = new StaffMapping();
		for (TblStaffEntity entity : entities) {

			models.add(mapping.entityToModel(entity));
		}

		return models;
	}

	private String parsingConTent(String emailType, StaffLeavingModel staffLeavingModel,
			EmailTemplateModel emailFormatModel) {

		String contentTemplate = emailFormatModel.getTemplateContent();

		if (TEMPLATE_LEAVE_REQ.equals(emailType)) {

			contentTemplate = contentTemplate.replace("{staffName}", staffLeavingModel.getStaffName());
			contentTemplate = contentTemplate.replace("{absentTypeName}", staffLeavingModel.getLeavingType());
			contentTemplate = contentTemplate.replace("{fromDate}",
					CommonFunc.dateToString(staffLeavingModel.getFromDate(), "dd/MM/yyyy"));
			contentTemplate = contentTemplate.replace("{toDate}",
					CommonFunc.dateToString(staffLeavingModel.getToDate(), "dd/MM/yyyy"));
			contentTemplate = contentTemplate.replace("{reason}", staffLeavingModel.getReason());

		} else if (TEMPLATE_LEAVE_RES.equals(emailType)) {

			contentTemplate = contentTemplate.replace("{approveStatus}",
					"1".equals(staffLeavingModel.getApproveStatus()) ? " approved " : " deny ");
			contentTemplate = contentTemplate.replace("{approveComment}",
					StringUtils.isEmpty(staffLeavingModel.getComment()) ? StringUtils.EMPTY
							: " with comment: " + staffLeavingModel.getComment());
			contentTemplate = contentTemplate.replace("{approvedUser}", staffLeavingModel.getApprovedUser());
		}
		return contentTemplate;
	}
}
