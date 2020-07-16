package infostaff.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import infostaff.common.CommonFunc;
import infostaff.common.CommonParam;
import infostaff.entity.TblStaffEntity;
import infostaff.entity.TblStaffTimeCardEntity;
import infostaff.exception.ResourceNotFoundException;
import infostaff.mapping.StaffTimeCardMapping;
import infostaff.model.StaffTimeCardModel;
import infostaff.repository.TblStaffRepository;
import infostaff.repository.TblStaffTimeCardRepository;
import infostaff.validation.StaffTimeCardValidation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StaffTimeCardServiceImpl implements IStaffTimeCardService {

	@Autowired
	TblStaffTimeCardRepository repo;

	@Autowired
	TblStaffRepository staffRepo;

	@Override
	public ResponseEntity<StaffTimeCardModel> checkIn(StaffTimeCardModel model, User user)
			throws ResourceNotFoundException {

		if (model != null) {

			StaffTimeCardValidation validation = new StaffTimeCardValidation();
			StaffTimeCardMapping mapping = new StaffTimeCardMapping();

			if (validation.cIValid(model)) {

				TblStaffTimeCardEntity entity = mapping.modelToEntity(model);

				if (entity != null) {

					// setup check in case
					entity.setCheckIn(CommonFunc.dateToString(new Date(), "hh:mm:ss"));
					entity.setChecked(true);
					entity.setWorkingDate(new Date());
					entity.setCreatedUser(user.getUsername());
					entity.setCreatedDate(new Date());
					entity.setRecordStatus(CommonParam.RC_OPEN);

					final StaffTimeCardModel insertedModel = mapping.entityToModel(repo.save(entity));
					return ResponseEntity.ok(insertedModel);

				} else {
					throw new ResourceNotFoundException("Parsing error");
				}
			} else {
				throw new ResourceNotFoundException("Validation error");
			}
		} else {
			throw new ResourceNotFoundException("Staff information is empty ");
		}
	}
	
	@Override
	public ResponseEntity<StaffTimeCardModel> checkOut(Long id, StaffTimeCardModel model, User user)
			throws ResourceNotFoundException {
		
		if (id != null) {

			StaffTimeCardMapping mapping = new StaffTimeCardMapping();

			TblStaffTimeCardEntity entity = repo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("StaffTimeCard not found for this id :: " + id));
			
			// setup check out case
			entity.setCheckOut(CommonFunc.dateToString(new Date(), "hh:mm:ss"));
			
			final StaffTimeCardModel updatededModel = mapping.entityToModel(repo.save(entity));
			return ResponseEntity.ok(updatededModel);
			
		} else {
			throw new ResourceNotFoundException("Staff information is empty ");
		}
	}
	
	@Override
	public ResponseEntity<StaffTimeCardModel> update(Long id, StaffTimeCardModel model, User user) 
			throws ResourceNotFoundException {
		if (id != null) {

			StaffTimeCardMapping mapping = new StaffTimeCardMapping();

			TblStaffTimeCardEntity entity = repo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("StaffTimeCard not found for this id :: " + id));
			
			// setup update case
			TblStaffTimeCardEntity parsingEntity = mapping.modelToEntity(model);
			parsingEntity.setId(entity.getId());
			
			final StaffTimeCardModel updatededModel = mapping.entityToModel(repo.save(parsingEntity));
			return ResponseEntity.ok(updatededModel);
			
		} else {
			throw new ResourceNotFoundException("Staff information is empty ");
		}
	}

	@Override
	public ResponseEntity<StaffTimeCardModel> getDailyChecking(User loginUser) throws ResourceNotFoundException {

		if (loginUser != null && StringUtils.isNotEmpty(loginUser.getUsername())) {

			StaffTimeCardModel staffTimeCardModel = new StaffTimeCardModel();

			// String currentDate = CommonFunc.dateToString(new Date(), "yyyy-MM-dd");
			Date currentDate = new Date();
			log.info("Current date:" + currentDate);
			log.info("User:" + loginUser.getUsername());

			try {
				TblStaffEntity staffEntity = staffRepo.findActivedStaff(loginUser.getUsername(), CommonParam.RC_OPEN);

				if (staffEntity != null) {
					log.info("Staff id:" + staffEntity.getStaffId());
					TblStaffTimeCardEntity entity = repo.findStaffDailyChecking(staffEntity.getStaffId(), true,
							currentDate, CommonParam.RC_OPEN);

					if (entity != null) {
						staffTimeCardModel = new StaffTimeCardMapping().entityToModel(entity);
					} else {
						staffTimeCardModel.setStaffId(staffEntity.getStaffId());
						staffTimeCardModel.setChecked(false);
					}

					return ResponseEntity.ok(staffTimeCardModel);
				}
			} catch (Exception ex) {
				throw new ResourceNotFoundException("Can not get user's daily timecard");
			}

		}
		throw new ResourceNotFoundException("Username is empty");
	}

//	@Override
//	public List<StaffTimeCardModel> getAll() {
//		List<StaffTimeCardModel> models = null;
//		List<TblStaffTimeCardEntity> entities = repo.findAll();
//
//		if (!entities.isEmpty()) {
//
//			models = new ArrayList<StaffTimeCardModel>();
//			StaffTimeCardModel model = new StaffTimeCardModel();
//			StaffTimeCardMapping roleMapping = new StaffTimeCardMapping();
//
//			for (TblStaffTimeCardEntity entity : entities) {
//
//				model = roleMapping.entityToModel(entity);
//
//				if (model != null)
//					models.add(model);
//			}
//		}
//
//		return models;
//	}
}
