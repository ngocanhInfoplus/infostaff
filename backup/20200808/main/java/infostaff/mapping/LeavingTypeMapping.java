package infostaff.mapping;

import infostaff.entity.TblLeavingTypeEntity;
import infostaff.model.LeavingTypeModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
public class LeavingTypeMapping {
	
	public LeavingTypeModel entityToModel(TblLeavingTypeEntity entity){
		
		LeavingTypeModel model  = new LeavingTypeModel();
		try{ 
			model.setLeavingCode(entity.getLeavingCode()); 
			model.setLeavingName(entity.getLeavingName()); 
			model.setCreatedUser(entity.getCreatedUser()); 
			model.setCreatedDate(entity.getCreatedDate()); 
			model.setRecordStatus(entity.getRecordStatus()); 
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	
	public TblLeavingTypeEntity modelToEntity(LeavingTypeModel model){
	
		TblLeavingTypeEntity entity  = new TblLeavingTypeEntity(); 
		try{ 
			entity.setLeavingCode(model.getLeavingCode()); 
			entity.setLeavingName(model.getLeavingName()); 
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
