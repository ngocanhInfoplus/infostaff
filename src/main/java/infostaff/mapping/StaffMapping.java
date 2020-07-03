package infostaff.mapping; 

import org.springframework.stereotype.Component;

import infostaff.entity.TblStaffEntity;
import infostaff.model.StaffModel;
import lombok.extern.slf4j.Slf4j; 


@Slf4j 
@Component 
public class StaffMapping{ 
	public StaffModel entityToModel(TblStaffEntity entity){ 
	
		StaffModel model  = new StaffModel(); 
		try{ 
			model.setStaffId(entity.getStaffId()); 
			model.setStaffCode(entity.getStaffCode()); 
			model.setStaffName(entity.getStaffName()); 
			model.setJoinDate(entity.getJoinDate()); 
			model.setDob(entity.getDob()); 
			model.setGender(entity.getGender()); 
			model.setLocationCode(entity.getLocationCode()); 
			model.setFileName(entity.getFileName()); 
			model.setCreatedUser(entity.getCreatedUser()); 
			model.setCreatedDate(entity.getCreatedDate()); 
			model.setChangedUser(entity.getChangedUser()); 
			model.setChangedDate(entity.getChangedDate()); 
			model.setRecordStatus(entity.getRecordStatus()); 
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	public TblStaffEntity modelToEntity(StaffModel model){ 
	
		TblStaffEntity entity  = new TblStaffEntity(); 
		try{ 
			entity.setStaffId(model.getStaffId()); 
			entity.setStaffCode(model.getStaffCode()); 
			entity.setStaffName(model.getStaffName()); 
			entity.setJoinDate(model.getJoinDate()); 
			entity.setDob(model.getDob()); 
			entity.setGender(model.getGender()); 
			entity.setLocationCode(model.getLocationCode()); 
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