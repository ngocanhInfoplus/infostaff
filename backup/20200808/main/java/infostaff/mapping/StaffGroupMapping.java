package infostaff.mapping; 

import org.springframework.stereotype.Component;

import infostaff.entity.TblStaffGroupEntity;
import infostaff.model.StaffGroupModel;
import lombok.extern.slf4j.Slf4j; 


@Slf4j 
@Component 
public class StaffGroupMapping{ 
	public StaffGroupModel entityToModel(TblStaffGroupEntity entity){ 
	
		StaffGroupModel model  = new StaffGroupModel(); 
		try{ 
			model.setId(entity.getId()); 
			model.setStaffId(entity.getStaffId()); 
			model.setGroupId(entity.getGroupId()); 
			model.setFromDate(entity.getFromDate()); 
			model.setToDate(entity.getToDate()); 
			model.setCreatedUser(entity.getCreatedUser()); 
			model.setCreatedDate(entity.getCreatedDate()); 
			model.setRecordStatus(entity.getRecordStatus()); 
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	public TblStaffGroupEntity modelToEntity(StaffGroupModel model){ 
	
		TblStaffGroupEntity entity  = new TblStaffGroupEntity(); 
		try{ 
			entity.setId(model.getId()); 
			entity.setStaffId(model.getStaffId()); 
			entity.setGroupId(model.getGroupId()); 
			entity.setFromDate(model.getFromDate()); 
			entity.setToDate(model.getToDate()); 
			entity.setCreatedUser(model.getCreatedUser()); 
			entity.setCreatedDate(model.getCreatedDate()); 
			entity.setRecordStatus(model.getRecordStatus()); 
	
			return entity; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
}