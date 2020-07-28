package infostaff.mapping;

import infostaff.entity.TblStaffLeavingEntity;
import infostaff.model.StaffLeavingModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
public class StaffLeavingMapping {

	public StaffLeavingModel entityToModel(TblStaffLeavingEntity entity){
		
		StaffLeavingModel model  = new StaffLeavingModel();
		try{ 
			model.setId(entity.getId()); 
			model.setStaffId(entity.getStaffId()); 
			model.setLeavingType(entity.getLeavingType()); 
			model.setTimeOffType(entity.getTimeOffType()); 
			model.setFromDate(entity.getFromDate()); 
			model.setToDate(entity.getToDate()); 
			model.setReason(entity.getReason()); 
			model.setManagerId01(entity.getManagerId01()); 
			model.setManagerId02(entity.getManagerId02()); 
			model.setComment(entity.getComment()); 
			model.setApproveStatus(entity.getApproveStatus()); 
			model.setFileName(entity.getFileName()); 
			model.setCreatedUser(entity.getCreatedUser()); 
			model.setCreatedDate(entity.getCreatedDate()); 
			model.setChangedUser(entity.getChangedUser()); 
			model.setChangedDate(entity.getChangedDate()); 
			model.setApprovedUser(entity.getApprovedUser()); 
			model.setApprovedDate(entity.getApprovedDate()); 
			model.setRecordStatus(entity.getRecordStatus());
			model.setTotalHour(entity.getTotalHour());
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	
	public TblStaffLeavingEntity modelToEntity(StaffLeavingModel model){
	
		TblStaffLeavingEntity entity  = new TblStaffLeavingEntity(); 
		try{ 
			entity.setId(model.getId()); 
			entity.setStaffId(model.getStaffId()); 
			entity.setLeavingType(model.getLeavingType()); 
			entity.setTimeOffType(model.getTimeOffType()); 
			entity.setFromDate(model.getFromDate()); 
			entity.setToDate(model.getToDate()); 
			entity.setReason(model.getReason()); 
			entity.setManagerId01(model.getManagerId01()); 
			entity.setManagerId02(model.getManagerId02()); 
			entity.setComment(model.getComment()); 
			entity.setApproveStatus(model.getApproveStatus()); 
			entity.setFileName(model.getFileName()); 
			entity.setCreatedUser(model.getCreatedUser()); 
			entity.setCreatedDate(model.getCreatedDate()); 
			entity.setChangedUser(model.getChangedUser()); 
			entity.setChangedDate(model.getChangedDate()); 
			entity.setApprovedUser(model.getApprovedUser()); 
			entity.setApprovedDate(model.getApprovedDate()); 
			entity.setRecordStatus(model.getRecordStatus());
			entity.setTotalHour(model.getTotalHour());
	
			return entity; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
}
