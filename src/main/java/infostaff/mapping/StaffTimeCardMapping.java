package infostaff.mapping; 

import org.springframework.stereotype.Component;

import infostaff.entity.TblStaffEntity;
import infostaff.entity.TblStaffTimeCardEntity;
import infostaff.model.StaffTimeCardModel;
import lombok.extern.slf4j.Slf4j; 


@Slf4j 
@Component 
public class StaffTimeCardMapping{ 
	public StaffTimeCardModel entityToModel(TblStaffTimeCardEntity entity){ 
	
		StaffTimeCardModel model  = new StaffTimeCardModel(); 
		try{ 
			model.setId(entity.getId()); 
			//model.setStaffId(entity.getStaffId());
			model.setStaffId(entity.getTblStaffEntity().getStaffId());
			model.setWorkingDate(entity.getWorkingDate()); 
			model.setCheckIn(entity.getCheckIn()); 
			model.setCheckOut(entity.getCheckOut()); 
			model.setChecked(entity.isChecked()); 
			model.setNote(entity.getNote()); 
			model.setFileName(entity.getFileName()); 
			model.setCreatedUser(entity.getCreatedUser()); 
			model.setCreatedDate(entity.getCreatedDate()); 
			model.setChangedUser(entity.getChangedUser()); 
			model.setChangedDate(entity.getChangedDate()); 
			model.setRecordStatus(entity.getRecordStatus()); 
			model.setStaffCode(entity.getTblStaffEntity().getStaffCode());
			model.setStaffName(entity.getTblStaffEntity().getStaffName());
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	public TblStaffTimeCardEntity modelToEntity(StaffTimeCardModel model){ 
	
		TblStaffTimeCardEntity entity  = new TblStaffTimeCardEntity(); 
		try{ 
			entity.setId(model.getId()); 
			//entity.setStaffId(model.getStaffId());
			TblStaffEntity staffEntity = new TblStaffEntity();
			staffEntity.setStaffId(model.getStaffId());
			entity.setTblStaffEntity(staffEntity);
			
			entity.setWorkingDate(model.getWorkingDate()); 
			entity.setCheckIn(model.getCheckIn()); 
			entity.setCheckOut(model.getCheckOut()); 
			entity.setChecked(model.isChecked()); 
			entity.setNote(model.getNote()); 
			entity.setFileName(model.getFileName()); 
			entity.setCreatedUser(model.getCreatedUser()); 
			entity.setCreatedDate(model.getCreatedDate()); 
			entity.setChangedUser(model.getChangedUser()); 
			entity.setChangedDate(model.getChangedDate()); 
			entity.setRecordStatus(model.getRecordStatus()); 
	
			return entity; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
}