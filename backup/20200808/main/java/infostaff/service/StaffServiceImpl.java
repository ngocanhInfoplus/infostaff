package infostaff.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import infostaff.common.CommonParam;
import infostaff.entity.TblStaffEntity;
import infostaff.entity.TblUserRoleEntity;
import infostaff.exception.ResourceNotFoundException;
import infostaff.mapping.StaffMapping;
import infostaff.mapping.UserRoleMapping;
import infostaff.model.SearchModel;
import infostaff.model.StaffModel;
import infostaff.model.UserModel;
import infostaff.model.UserRoleModel;
import infostaff.repository.TblStaffRepository;
import infostaff.repository.TblUserRoleRepository;
import infostaff.validation.StaffValidation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StaffServiceImpl implements IStaffService {

	@Autowired
	TblStaffRepository staffRepo;

	@Autowired
	TblUserRoleRepository userRoleRepo;

	@Autowired
	private IUserService userService;

	@Autowired
	StaffMapping staffMapping;

	@Autowired
	UserRoleMapping userRoleMapping;

	@Override
	public List<StaffModel> getDirectManager(User user) {

		List<TblStaffEntity> entities = staffRepo.findManagerByUserName(user.getUsername(), CommonParam.RC_OPEN);

		if (entities == null || entities.size() == 0)
			return null;

		StaffMapping mapping = new StaffMapping();
		List<StaffModel> models = new ArrayList<StaffModel>();

		for (TblStaffEntity entity : entities) {
			models.add(mapping.entityToModel(entity));
		}
		return models;

	}

	@Override
	public List<StaffModel> getAllManager(User user) {

		List<TblStaffEntity> entities = staffRepo.findAllManager(CommonParam.RC_OPEN);

		if (entities == null || entities.size() == 0)
			return null;

		StaffMapping mapping = new StaffMapping();
		List<StaffModel> models = new ArrayList<StaffModel>();

		for (TblStaffEntity entity : entities) {
			models.add(mapping.entityToModel(entity));
		}
		return models;
	}

	@Override
	public List<StaffModel> findStaffs(User user, SearchModel searchModel) {

		String locationCode = null;
		String departmentCode = null;
		String countryCode = null;
		String jobTypeCode = null;

		if (StringUtils.isNotBlank(searchModel.getLocationCode()) && !"-1".equals(searchModel.getLocationCode()))
			locationCode = searchModel.getLocationCode();

		if (StringUtils.isNotBlank(searchModel.getDepartmentCode()) && !"-1".equals(searchModel.getDepartmentCode()))
			departmentCode = searchModel.getDepartmentCode();

		if (StringUtils.isNotBlank(searchModel.getCountryCode()) && !"-1".equals(searchModel.getCountryCode()))
			countryCode = searchModel.getCountryCode();

		if (StringUtils.isNotBlank(searchModel.getJobTitleCode()) && !"-1".equals(searchModel.getJobTitleCode()))
			jobTypeCode = searchModel.getJobTitleCode();

		List<TblStaffEntity> entities = staffRepo.findStaffs(locationCode, departmentCode, countryCode, jobTypeCode,
				CommonParam.RC_OPEN);

		if (entities == null || entities.isEmpty())
			return null;

		return staffMapping.entitiesToModels(entities);
	}

	@Override
	public StaffModel create(User user, StaffModel insertModel) throws ResourceNotFoundException {
		StaffValidation validation = new StaffValidation();

		if (!validation.validate(insertModel))
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		TblStaffEntity entity = staffMapping.modelToEntity(insertModel);

		if (entity == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);

		entity.setStaffCode(getStaffCode(insertModel.getJobTitleCode(), insertModel.getJoinDate()));
		entity.setRecordStatus(CommonParam.RC_OPEN);
		entity.setCreatedUser(user.getUsername());
		entity.setCreatedDate(new Date());

		// setup for User
		UserModel userModel = new UserModel();
		userModel.setUserName(insertModel.getUserName());

		try {
			// insert data to db
			final StaffModel insertedModel = staffMapping.entityToModel(staffRepo.save(entity));

			// inser user
			userService.create(user, userModel);
			return insertedModel;
		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	@Override
	public StaffModel update(User user, Long requestId, StaffModel model) throws ResourceNotFoundException {

		StaffValidation validation = new StaffValidation();

		if (requestId == null || !validation.validate(model))
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// parsing data
		TblStaffEntity newEntity = staffMapping.modelToEntity(model);

		if (newEntity == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);

		// find old data
		TblStaffEntity oldEntity = staffRepo.findById(requestId)
				.orElseThrow(() -> new ResourceNotFoundException("The request not found for this id :: " + requestId));

		try {
			// setup update
			newEntity.setStaffId(requestId);
			newEntity.setRecordStatus(CommonParam.RC_OPEN);
			newEntity.setCreatedUser(oldEntity.getCreatedUser());
			newEntity.setCreatedDate(oldEntity.getCreatedDate());
			newEntity.setChangedUser(user.getUsername());
			newEntity.setChangedDate(new Date());

			final StaffModel updatedModel = staffMapping.entityToModel(staffRepo.save(newEntity));
			return updatedModel;

		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	@Override
	public StaffModel cancel(User user, Long id) throws ResourceNotFoundException {

		if (id == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// find old data
		TblStaffEntity oldEntity = staffRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("The request not found for this id :: " + id));

		try {
			// setup update
			oldEntity.setRecordStatus(CommonParam.RC_CLOSE);
			oldEntity.setChangedUser(user.getUsername());
			oldEntity.setChangedDate(new Date());

			final StaffModel updatedModel = staffMapping.entityToModel(staffRepo.save(oldEntity));

			userService.disableUser(user, oldEntity.getUserName());
			return updatedModel;

		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	@Override
	public UserRoleModel addRole(User user, UserRoleModel model) throws ResourceNotFoundException {

		if (model == null || StringUtils.isEmpty(model.getUserName()) || model.getRoleId() == null
				|| model.getRoleId() == -1)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// find old data
		List<TblUserRoleEntity> oldEntities = userRoleRepo.getByUserName(model.getUserName());
		TblUserRoleEntity newEntity = userRoleMapping.modelToEntity(model);
		newEntity.setCreatedUser(user.getUsername()); 
		newEntity.setCreatedDate(new Date());

		try {
			if (oldEntities != null) {
				userRoleRepo.deleteByUserName(model.getUserName());
			}

			userRoleRepo.insert(newEntity.getTblUserEntity().getUserName(), newEntity.getTblRoleEntity().getRoleId());
			return model;
		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}

	}

	private String getStaffCode(String jobTitleCode, Date joinedDate) {
		return StringUtils.EMPTY;
	}

}
