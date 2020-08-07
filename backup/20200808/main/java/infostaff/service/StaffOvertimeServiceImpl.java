package infostaff.service;

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
import infostaff.entity.TblStaffOvertimeEntity;
import infostaff.exception.ResourceNotFoundException;
import infostaff.mapping.StaffOvertimeMapping;
import infostaff.model.SearchModel;
import infostaff.model.StaffOvertimeModel;
import infostaff.repository.TblEmailStaffRepository;
import infostaff.repository.TblEmailTemplateRepository;
import infostaff.repository.TblOvertimeTypeRepository;
import infostaff.repository.TblStaffOvertimeRepository;
import infostaff.repository.TblStaffRepository;
import infostaff.validation.StaffOvertimeValidation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StaffOvertimeServiceImpl implements IStaffOvertimeService {

	@Autowired
	TblStaffRepository staffRepo;

	@Autowired
	TblOvertimeTypeRepository overtimeRepo;

	@Autowired
	TblStaffOvertimeRepository staffOvertimeRepo;

	@Autowired
	TblEmailTemplateRepository emailTemplateRepo;
	
	@Autowired
	TblEmailStaffRepository emailStaffRepo;

	@Autowired
	IEmailService emailService;

	@Autowired
	StaffOvertimeMapping staffOvertimeMapping;

	final String TEMPLATE_OT_REQ = "OT_REQ";
	final String TEMPLATE_OT_RES = "OT_RES";

	@Override
	public List<StaffOvertimeModel> getByUser(User user, SearchModel searchModel) throws ResourceNotFoundException {

		// get role
		String roleName = CommonFunc.getRoleName(user);

		if (StringUtils.isEmpty(roleName))
			return null;

		switch (roleName) {
		case CommonParam.ROLE_USER:
			return getByStaffUser(user, searchModel);

		case CommonParam.ROLE_ADMIN:
		case CommonParam.ROLE_MANAGER:
			return getByManagerUser(user, searchModel);
		}

		return null;
	}

	private List<StaffOvertimeModel> getByStaffUser(User user, SearchModel searchModel)
			throws ResourceNotFoundException {

		TblStaffEntity staffEntity = staffRepo.findActivedStaff(user.getUsername(), CommonParam.RC_OPEN);

		if (staffEntity == null)
			return null;

		List<TblStaffOvertimeEntity> entities = null;
		String approveStatus = searchModel.getApproveStatus();
		Date fromDate = searchModel.getFromDate();
		Date toDate = searchModel.getToDate();
		Long staffId = staffEntity.getStaffId();

		if (StringUtils.isEmpty(approveStatus) || "-1".equals(approveStatus))
			// approve status = all
			entities = staffOvertimeRepo.findByStaff(staffId, fromDate, toDate, CommonParam.RC_OPEN);

		else
			// approve status = <value>
			entities = staffOvertimeRepo.findByStaff(staffId, approveStatus, fromDate, toDate, CommonParam.RC_OPEN);

		return staffOvertimeMapping.entitiesToModels(entities);
	}

	private List<StaffOvertimeModel> getByManagerUser(User user, SearchModel searchModel) {

		TblStaffEntity staffEntity = staffRepo.findActivedStaff(user.getUsername(), CommonParam.RC_OPEN);

		if (staffEntity == null)
			return null;

		List<TblStaffOvertimeEntity> entities = null;
		String approveStatus = searchModel.getApproveStatus();
		Date fromDate = searchModel.getFromDate();
		Date toDate = searchModel.getToDate();
		Long staffId = staffEntity.getStaffId();

		if (StringUtils.isEmpty(approveStatus) || "-1".equals(approveStatus))
			// search all
			entities = staffOvertimeRepo.findByManager(staffId, fromDate, toDate, CommonParam.RC_OPEN);

		else
			// search by approve status
			entities = staffOvertimeRepo.findByManager(staffId, approveStatus, fromDate, toDate, CommonParam.RC_OPEN);

		return staffOvertimeMapping.entitiesToModels(entities);
	}

	@Override
	public StaffOvertimeModel createRequest(User user, StaffOvertimeModel insertModel)
			throws ResourceNotFoundException {

		StaffOvertimeValidation validation = new StaffOvertimeValidation();
		StaffOvertimeMapping mapping = new StaffOvertimeMapping();

		if (!validation.validate(insertModel))
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// parsing data
		TblStaffEntity staffEntity = staffRepo.findActivedStaff(user.getUsername(), CommonParam.RC_OPEN);

		if (staffEntity == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);

		TblStaffOvertimeEntity entity = mapping.modelToEntity(insertModel);

		if (entity == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);

		entity.getTblStaffEntity().setStaffId(staffEntity.getStaffId());
		entity.setApproveStatus("I");
		entity.setTotalHour(
				getTotalOTHour(insertModel.getFromTime(), insertModel.getToTime(), insertModel.isRestTime()));
		entity.setRecordStatus(CommonParam.RC_OPEN);
		entity.setCreatedUser(user.getUsername());
		entity.setCreatedDate(new Date());

		try {
			// insert data to db
			final StaffOvertimeModel insertedModel = mapping.entityToModel(staffOvertimeRepo.save(entity));
			return insertedModel;
		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	@Override
	public StaffOvertimeModel updateRequest(User user, Long requestId, StaffOvertimeModel model)
			throws ResourceNotFoundException {

		StaffOvertimeValidation validation = new StaffOvertimeValidation();
		StaffOvertimeMapping mapping = new StaffOvertimeMapping();

		if (requestId == null || !validation.validate(model))
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// parsing data
		TblStaffOvertimeEntity entity = mapping.modelToEntity(model);

		if (entity == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);

		// find old data
		TblStaffOvertimeEntity oldEntity = staffOvertimeRepo.findById(requestId)
				.orElseThrow(() -> new ResourceNotFoundException("The request not found for this id :: " + requestId));

		if (!"I".equals(oldEntity.getApproveStatus()))
			throw new ResourceNotFoundException("The request is send to approve");

		// setup update
		entity.setId(requestId);
		entity.setTotalHour(getTotalOTHour(model.getFromTime(), model.getToTime(), model.isRestTime()));
		entity.setRecordStatus(CommonParam.RC_OPEN);
		entity.setCreatedUser(oldEntity.getCreatedUser());
		entity.setCreatedDate(oldEntity.getCreatedDate());
		entity.setChangedUser(user.getUsername());
		entity.setChangedDate(new Date());

		try {

			final StaffOvertimeModel updatedModel = mapping.entityToModel(staffOvertimeRepo.save(entity));
			return updatedModel;

		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	@Override
	public StaffOvertimeModel approveRequest(User user, Long requestId, StaffOvertimeModel model)
			throws ResourceNotFoundException {

		if (requestId == null || model == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// parsing data
		TblStaffOvertimeEntity newEntity = staffOvertimeMapping.modelToEntity(model);

		if (newEntity == null || StringUtils.isEmpty(newEntity.getApproveStatus()))
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);

		// find old data
		TblStaffOvertimeEntity oldEntity = staffOvertimeRepo.findById(requestId)
				.orElseThrow(() -> new ResourceNotFoundException("The request not found for this id :: " + requestId));
		
		if ("A".equals(oldEntity.getApproveStatus()) || "D".equals(oldEntity.getApproveStatus()))
			throw new ResourceNotFoundException("The request was approved/denied by manager");

		try {
			oldEntity.setApproveStatus(newEntity.getApproveStatus());
			oldEntity.setComment(newEntity.getComment());
			oldEntity.setApprovedUser(user.getUsername());
			oldEntity.setApprovedDate(new Date());

			final StaffOvertimeModel updatedModel = staffOvertimeMapping
					.entityToModel(staffOvertimeRepo.save(oldEntity));

			return updatedModel;

		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	@Override
	public StaffOvertimeModel cancelRequest(User user, Long id) throws ResourceNotFoundException {

		if (id == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// find old data
		TblStaffOvertimeEntity oldEntity = staffOvertimeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("The request not found for this id :: " + id));

		if (!"I".equals(oldEntity.getApproveStatus()))
			throw new ResourceNotFoundException("The request is send to approve");

		try {
			// setup update
			oldEntity.setRecordStatus(CommonParam.RC_CLOSE);
			oldEntity.setChangedUser(user.getUsername());
			oldEntity.setChangedDate(new Date());

			final StaffOvertimeModel updatedModel = staffOvertimeMapping
					.entityToModel(staffOvertimeRepo.save(oldEntity));
			return updatedModel;

		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	@Override
	public StaffOvertimeModel sendRequest(User user, Long requestId) throws ResourceNotFoundException {

		if (requestId == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// find old data
		TblStaffOvertimeEntity oldEntity = staffOvertimeRepo.findById(requestId).orElseThrow(
				() -> new ResourceNotFoundException("The request not found for this id :: " + requestId));

		if ("A".equals(oldEntity.getApproveStatus()) || "D".equals(oldEntity.getApproveStatus()))
			throw new ResourceNotFoundException("The request was approved/denied by manager");

		try {
			oldEntity.setApproveStatus("W");
			StaffOvertimeModel updatedModel = staffOvertimeMapping.entityToModel(staffOvertimeRepo.save(oldEntity));
			sendEmail(oldEntity, TEMPLATE_OT_REQ);
			return updatedModel;

		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	@Override
	public StaffOvertimeModel createAndSendRequest(User user, StaffOvertimeModel model)
			throws ResourceNotFoundException {
		
		StaffOvertimeModel insetedModel = createRequest(user, model);

		if (insetedModel != null)
			return sendRequest(user, insetedModel.getId());

		return null;
	}

	private float getTotalOTHour(String fromDate, String toDate, boolean restTime) {
		Date dFromDate = CommonFunc.getDateTime(fromDate, CommonParam.DEFAULT_DT_FORMAT);
		Date dToDate = CommonFunc.getDateTime(toDate, CommonParam.DEFAULT_DT_FORMAT);

		return CommonFunc.hoursDifference(dToDate, dFromDate, restTime);
	}
	
	private String sendEmail(TblStaffOvertimeEntity entity, String templateCode) {

		if (entity == null || StringUtils.isEmpty(templateCode))
			return "Dont have information to send!";

		String emailTo = StringUtils.EMPTY;
		String ccTo = StringUtils.EMPTY;
		String subject = StringUtils.EMPTY;
		String tempContent = StringUtils.EMPTY;
		String content = StringUtils.EMPTY;

		if (TEMPLATE_OT_REQ.equals(templateCode)) {
			// get managers info
			String managerIds = CommonFunc.getMangerIdsString(entity.getManagerId01(), entity.getManagerId02());
			
			List<TblStaffEntity> managerEntites = staffRepo.findManagers(managerIds, CommonParam.RC_OPEN);
			
			emailTo = CommonFunc.getEmailString(managerEntites);

		} else if (TEMPLATE_OT_RES.equals(templateCode)) {
			// get staff info
			emailTo = entity.getTblStaffEntity().getEmail();
		}

		// get default staffs info
		List<TblStaffEntity> defaultStaffs = emailStaffRepo.findStaffByEmailTemplateCode(templateCode,
				CommonParam.RC_OPEN);
		ccTo = CommonFunc.getEmailString(defaultStaffs);

		TblEmailTemplateEntity emailTemplateEntity = emailTemplateRepo.findByTemplateCode(templateCode);

		subject = (emailTemplateEntity == null) ? "NO SUBJECT" : emailTemplateEntity.getTemplateSubject();
		tempContent = (emailTemplateEntity == null) ? "NO CONTENT" : emailTemplateEntity.getTemplateContent();
		content = parsingConTent(templateCode, tempContent, entity);

		emailService.sendSimpleHtmlMessage(emailTo, ccTo, subject, content);

		return "Send success";

	}
	
	private String parsingConTent(String emailType, String templateContent, TblStaffOvertimeEntity entity) {

		String content = templateContent;

		if (TEMPLATE_OT_REQ.equals(emailType)) {

			content = content.replace("{staffName}", entity.getTblStaffEntity().getStaffName());
			content = content.replace("{fromDate}", CommonFunc.dateToString(entity.getFromTime(), "dd/MM/yyyy"));
			content = content.replace("{toDate}", CommonFunc.dateToString(entity.getToTime(), "dd/MM/yyyy"));

		} else if (TEMPLATE_OT_RES.equals(emailType)) {

			content = content.replace("{approveStatus}",
					"A".equals(entity.getApproveStatus()) ? " approved " : " denied ");
			content = content.replace("{approveComment}", StringUtils.isEmpty(entity.getComment()) ? StringUtils.EMPTY
					: " with comment: " + entity.getComment());
			content = content.replace("{approvedUser}", entity.getApprovedUser());
		}
		return content;
	}

}
