package infostaff.mapping; 

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import infostaff.common.CommonFunc;
import infostaff.common.CommonParam;
import infostaff.entity.TblOvertimeTypeEntity;
import infostaff.entity.TblStaffEntity;
import infostaff.entity.TblStaffOvertimeEntity;
import infostaff.model.OvertimeTypeModel;
import infostaff.model.StaffModel;
import infostaff.model.StaffOvertimeModel;
import lombok.extern.slf4j.Slf4j; 


@Slf4j 
@Component 
public class StaffOvertimeMapping{ 
	public StaffOvertimeModel entityToModel(TblStaffOvertimeEntity entity){ 
	
		StaffOvertimeModel model  = new StaffOvertimeModel(); 
		try{ 
			model.setId(entity.getId());
			
			StaffMapping staffMapping = new StaffMapping();
			StaffModel staffModel = staffMapping.entityToModel(entity.getTblStaffEntity());
			model.setStaffModel(staffModel);
			
			//model.setStaffId(entity.getStaffId()); 
			model.setRegisterDate(entity.getRegisterDate()); 
			model.setFromTime(CommonFunc.getTimeString(entity.getFromTime(), CommonParam.DEFAULT_DT_FORMAT)); 
			model.setToTime(CommonFunc.getTimeString(entity.getToTime(), CommonParam.DEFAULT_DT_FORMAT)); 
			model.setProjectName(entity.getProjectName()); 
			
			OvertimeTypeMapping mapping = new OvertimeTypeMapping();
			OvertimeTypeModel otModel = mapping.entityToModel(entity.getTblOvertimeTypeEntity());
			model.setOtType(otModel); 
			
			model.setApproveStatus(entity.getApproveStatus());
			model.setApproveStatusName(CommonFunc.getApproveStatusName(entity.getApproveStatus()));
			
			model.setManagerId01(entity.getManagerId01()); 
			model.setManagerId02(entity.getManagerId02()); 
			model.setCreatedUser(entity.getCreatedUser()); 
			model.setCreatedDate(entity.getCreatedDate()); 
			model.setChangedUser(entity.getChangedUser()); 
			model.setChangedDate(entity.getChangedDate()); 
			model.setApprovedUser(entity.getApprovedUser()); 
			model.setApprovedDate(entity.getApprovedDate()); 
			model.setRecordStatus(entity.getRecordStatus()); 
			model.setRestTime(entity.isRestTime());
			model.setOtOvernight(entity.isOtOvernight());
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	
	public TblStaffOvertimeEntity modelToEntity(StaffOvertimeModel model){ 
	
		TblStaffOvertimeEntity entity  = new TblStaffOvertimeEntity(); 
		try{ 
			StaffMapping staffMapping = new StaffMapping();
			TblStaffEntity staffEntity = staffMapping.modelToEntity(model.getStaffModel());
			entity.setTblStaffEntity(staffEntity);
			
			//entity.setStaffId(model.getStaffId()); 
			entity.setRegisterDate(model.getRegisterDate()); 
			entity.setFromTime(CommonFunc.getDateTime(model.getFromTime(), CommonParam.DEFAULT_DT_FORMAT)); 
			entity.setToTime(CommonFunc.getDateTime(model.getToTime(), CommonParam.DEFAULT_DT_FORMAT)); 
			entity.setProjectName(model.getProjectName()); 
			
			OvertimeTypeMapping oMapping = new OvertimeTypeMapping();
			TblOvertimeTypeEntity otEntity = oMapping.modelToEntity(model.getOtType());
			entity.setTblOvertimeTypeEntity(otEntity); 
			
			entity.setApproveStatus(model.getApproveStatus());
			
			entity.setManagerId01(model.getManagerId01()); 
			entity.setManagerId02(model.getManagerId02()); 
			entity.setCreatedUser(model.getCreatedUser()); 
			entity.setCreatedDate(model.getCreatedDate()); 
			entity.setChangedUser(model.getChangedUser()); 
			entity.setChangedDate(model.getChangedDate()); 
			entity.setApprovedUser(model.getApprovedUser()); 
			entity.setApprovedDate(model.getApprovedDate()); 
			entity.setRecordStatus(model.getRecordStatus()); 
			entity.setRestTime(model.isRestTime());
			entity.setOtOvernight(model.isOtOvernight());
			
			return entity; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 

	public List<StaffOvertimeModel> entitiesToModels(List<TblStaffOvertimeEntity> entities){
		
		if (entities == null || entities.isEmpty())
			return null;
		
		log.error("Total records from DB: " + entities.size()); 
		
		StaffOvertimeModel model;
		List<StaffOvertimeModel> models = new ArrayList<StaffOvertimeModel>();

		for (TblStaffOvertimeEntity entity : entities) {
			model = entityToModel(entity);

			if (model != null)
				models.add(model);
		}

		log.error("Total records from BE: " + models.size()); 
		return models;
	}
}