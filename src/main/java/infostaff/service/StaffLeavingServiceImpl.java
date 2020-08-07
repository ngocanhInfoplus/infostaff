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
import infostaff.mapping.StaffLeavingMapping;
import infostaff.model.StaffAnnualLeaveModel;
import infostaff.model.StaffLeavingModel;
import infostaff.model.StaffLeavingSearchModel;
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

		case CommonParam.ROLE_ADMIN:
		case CommonParam.ROLE_MANAGER:
			return searchByManagerUser(user, searchModel);
		}

		return null;
	}

	private List<StaffLeavingModel> searchByStaffUser(User user, StaffLeavingSearchModel searchModel) {

		TblStaffEntity staffEntity = staffRepo.findActivedStaff(user.getUsername(), CommonParam.RC_OPEN);

		if (staffEntity == null)
			return null;

		log.info("input - staffId: " + staffEntity.getStaffId() + ",fromDate: " + searchModel.getFromDate()
				+ ",toDate: " + searchModel.getToDate() + ", approveStatus: " + searchModel.getApproveStatus());

		List<TblStaffLeavingEntity> entities = new ArrayList<TblStaffLeavingEntity>();

		if ("-1".equals(searchModel.getApproveStatus())) {
			// approve status = all
			entities = staffLeavingRepo.findActiveRequestByStaffIdAndDate(staffEntity.getStaffId(),
					searchModel.getFromDate(), searchModel.getToDate(), CommonParam.RC_OPEN);
		} else {
			// approve status = <value>
			entities = staffLeavingRepo.findActiveRequestByStaffIdAndDateAndApproveStatus(staffEntity.getStaffId(),
					searchModel.getApproveStatus(), searchModel.getFromDate(), searchModel.getToDate(),
					CommonParam.RC_OPEN);
		}

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

		TblStaffEntity staffEntity = staffRepo.findActivedStaff(user.getUsername(), CommonParam.RC_OPEN);

		if (staffEntity == null)
			return null;

		List<TblStaffLeavingEntity> entities = null;

		if (StringUtils.isEmpty(searchModel.getApproveStatus()) || "-1".equals(searchModel.getApproveStatus()))
			// search all
			entities = staffLeavingRepo.findActiveRequestByManagerAndDate(staffEntity.getStaffId(),
					searchModel.getFromDate(), searchModel.getToDate(), CommonParam.RC_OPEN);

		else
			// search by approve status
			entities = staffLeavingRepo.findActiveRequestByManagerAndDateAndApproveStatus(staffEntity.getStaffId(),
					searchModel.getApproveStatus(), searchModel.getFromDate(), searchModel.getToDate(),
					CommonParam.RC_OPEN);

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
		TblStaffEntity staffEntity = staffRepo.findActivedStaff(user.getUsername(), CommonParam.RC_OPEN);

		if (staffEntity == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);

		TblStaffLeavingEntity entity = mapping.modelToEntity(insertModel);

		if (entity == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);

		entity.getTblStaffEntity().setStaffId(staffEntity.getStaffId());
		entity.setApproveStatus("I");
		entity.setTotalDay(getTotalDay(insertModel));
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

		// find old data
		TblStaffLeavingEntity oldEntity = staffLeavingRepo.findById(updateId).orElseThrow(
				() -> new ResourceNotFoundException("The leaving request not found for this id :: " + updateId));

		if (!"I".equals(oldEntity.getApproveStatus()))
			throw new ResourceNotFoundException("The leaving request is send to approve");

		try {
			// setup update
			newEntity.setId(updateId);
			newEntity.setTotalDay(getTotalDay(newModel));
			newEntity.setRecordStatus(CommonParam.RC_OPEN);
			newEntity.setCreatedUser(oldEntity.getCreatedUser());
			newEntity.setCreatedDate(oldEntity.getCreatedDate());
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
	public StaffLeavingModel cancelRequest(User user, Long id) throws ResourceNotFoundException {

		if (id == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// find old data
		TblStaffLeavingEntity oldEntity = staffLeavingRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("The leaving request not found for this id :: " + id));

		if (!"I".equals(oldEntity.getApproveStatus()))
			throw new ResourceNotFoundException("The leaving request is send to approve");

		try {
			StaffLeavingMapping mapping = new StaffLeavingMapping();
			// setup update
			oldEntity.setRecordStatus(CommonParam.RC_CLOSE);
			oldEntity.setChangedUser(user.getUsername());
			oldEntity.setChangedDate(new Date());

			final StaffLeavingModel updatedModel = mapping.entityToModel(staffLeavingRepo.save(oldEntity));
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

		// find old data
		TblStaffLeavingEntity oldEntity = staffLeavingRepo.findById(requestId).orElseThrow(
				() -> new ResourceNotFoundException("The leaving request not found for this id :: " + requestId));

		if ("A".equals(oldEntity.getApproveStatus()) || "D".equals(oldEntity.getApproveStatus()))
			throw new ResourceNotFoundException("The leaving request was approved/denied by manager");

		try {
			oldEntity.setApproveStatus(newEntity.getApproveStatus());
			oldEntity.setComment(newEntity.getComment());
			oldEntity.setApprovedUser(user.getUsername());
			oldEntity.setApprovedDate(new Date());

			StaffLeavingModel updatedModel = mapping.entityToModel(staffLeavingRepo.save(oldEntity));
			sendStaffLeavingEmail(oldEntity, TEMPLATE_LEAVE_RES);
			return updatedModel;

		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	@Override
	public StaffLeavingModel sendRequest(User user, Long requestId) throws ResourceNotFoundException {

		StaffLeavingMapping mapping = new StaffLeavingMapping();

		if (requestId == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// find old data
		TblStaffLeavingEntity oldEntity = staffLeavingRepo.findById(requestId).orElseThrow(
				() -> new ResourceNotFoundException("The leaving request not found for this id :: " + requestId));

		if ("A".equals(oldEntity.getApproveStatus()) || "D".equals(oldEntity.getApproveStatus()))
			throw new ResourceNotFoundException("The leaving request was approved/denied by manager");

		try {
			oldEntity.setApproveStatus("W");
			StaffLeavingModel updatedModel = mapping.entityToModel(staffLeavingRepo.save(oldEntity));
			// StaffLeavingModel updatedModel = mapping.entityToModel(oldEntity);
			sendStaffLeavingEmail(oldEntity, TEMPLATE_LEAVE_REQ);
			return updatedModel;

		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	@Override
	public StaffAnnualLeaveModel getStaffAnnualLeaveSummary(User user, StaffLeavingSearchModel model) {

		if (model == null)
			return null;

		TblStaffEntity staffEntity = null;
		if (model.getStaffId() == null) {

			staffEntity = staffRepo.findActivedStaff(user.getUsername(), CommonParam.RC_OPEN);
		}else {
			
			staffEntity = staffRepo.findByStaffId(model.getStaffId(), CommonParam.RC_OPEN);
		}
		
		if (staffEntity == null)
			return null;
		
		try {
			Float advanceAnnualLeave = 0F;
			Float usedAnnualLeave = 0F;

			if (staffEntity != null)
				advanceAnnualLeave = staffEntity.getAdvanceAnnualLeave();

			usedAnnualLeave = staffLeavingRepo.getUsedAnnualLeaveDay(staffEntity.getStaffId(), model.getFromDate(),
					model.getToDate(), CommonParam.RC_OPEN);
			if (usedAnnualLeave == null)
				usedAnnualLeave = 0F;

			return new StaffAnnualLeaveModel(staffEntity.getStaffId(), usedAnnualLeave, advanceAnnualLeave);
		} catch (Exception ex) {
			return new StaffAnnualLeaveModel(staffEntity.getStaffId(), 0F, 0F);
		}
	}

	@Override
	public StaffLeavingModel createAndSendRequest(User user, StaffLeavingModel insertModel)
			throws ResourceNotFoundException {

		StaffLeavingModel insetedModel = createRequest(user, insertModel);

		if (insertModel != null)
			return sendRequest(user, insetedModel.getId());

		return null;
	}

	private String sendStaffLeavingEmail(TblStaffLeavingEntity entity, String templateCode) {

		if (entity == null || StringUtils.isEmpty(templateCode))
			return "Dont have information to send!";

		String emailTo = StringUtils.EMPTY;
		String ccTo = StringUtils.EMPTY;
		String subject = StringUtils.EMPTY;
		String tempContent = StringUtils.EMPTY;
		String content = StringUtils.EMPTY;

		if (TEMPLATE_LEAVE_REQ.equals(templateCode)) {
			// get manager info
			Long managerId = (entity.getManagerId02() == null) ? entity.getManagerId01() : entity.getManagerId02();
			TblStaffEntity managerStaffEntity = staffRepo.findById(managerId).orElse(null);
			emailTo = (managerStaffEntity == null) ? StringUtils.EMPTY : managerStaffEntity.getEmail();

		} else if (TEMPLATE_LEAVE_RES.equals(templateCode)) {
			// get staff info
			emailTo = entity.getTblStaffEntity().getEmail();
		}

		// get group staffs info
		String groupCode = entity.getTblStaffEntity().getGroupEntity().getGroupCode();
		log.info("GroupId : " + groupCode);
		List<TblStaffEntity> staffEntities = staffRepo.findStaffsByGroupCode(groupCode, CommonParam.RC_OPEN);
		ccTo = CommonFunc.getEmailString(staffEntities);

		// get default staffs info
		List<TblStaffEntity> defaultStaffs = emailStaffRepo.findStaffByEmailTemplateCode(templateCode,
				CommonParam.RC_OPEN);

		if (StringUtils.isNotEmpty(ccTo))
			ccTo += "," + CommonFunc.getEmailString(defaultStaffs);
		else
			ccTo = CommonFunc.getEmailString(defaultStaffs);

		TblEmailTemplateEntity emailTemplateEntity = emailTemplateRepo.findByTemplateCode(templateCode);

		subject = (emailTemplateEntity == null) ? "NO SUBJECT" : emailTemplateEntity.getTemplateSubject();
		tempContent = (emailTemplateEntity == null) ? "NO CONTENT" : emailTemplateEntity.getTemplateContent();
		content = parsingConTent(templateCode, tempContent, entity);

		emailService.sendSimpleHtmlMessage(emailTo, ccTo, subject, content);

		return "Send success";

	}

	private String parsingConTent(String emailType, String templateContent, TblStaffLeavingEntity entity) {

		String content = templateContent;

		if (TEMPLATE_LEAVE_REQ.equals(emailType)) {

			content = content.replace("{staffName}", entity.getTblStaffEntity().getStaffName());
			content = content.replace("{absentTypeName}", entity.getTblLeavingTypeEntity().getLeavingName());
			content = content.replace("{fromDate}", CommonFunc.dateToString(entity.getFromDate(), "dd/MM/yyyy"));
			content = content.replace("{toDate}", CommonFunc.dateToString(entity.getToDate(), "dd/MM/yyyy"));
			content = content.replace("{reason}", entity.getReason());

		} else if (TEMPLATE_LEAVE_RES.equals(emailType)) {

			content = content.replace("{approveStatus}",
					"A".equals(entity.getApproveStatus()) ? " approved " : " denied ");
			content = content.replace("{approveComment}", StringUtils.isEmpty(entity.getComment()) ? StringUtils.EMPTY
					: " with comment: " + entity.getComment());
			content = content.replace("{approvedUser}", entity.getApprovedUser());
		}
		return content;
	}

	private float getTotalDay(StaffLeavingModel model) {

		switch (model.getTimeOffCode()) {
		case CommonParam.TIMEOFF_HALF:
			return (float) 0.5;
		case CommonParam.TIMEOFF_FULL:
			return (float) 1;
		case CommonParam.TIMEOFF_MORE:
			return CommonFunc.daysDifference(model.getToDate(), model.getFromDate());
		}
		return 0;
	}
}
