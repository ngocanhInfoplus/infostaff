package infostaff.mapping;

import infostaff.entity.TblTimeOffEntity;
import infostaff.model.TimeOffModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeOffMapping {
	
	public TimeOffModel entityToModel(TblTimeOffEntity entity){ 
		
		TimeOffModel model  = new TimeOffModel(); 
		try{ 
			model.setCode(entity.getCode()); 
			model.setName(entity.getName()); 
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	
	public TblTimeOffEntity modelToEntity(TimeOffModel model){ 
	
		TblTimeOffEntity entity  = new TblTimeOffEntity(); 
		try{ 
			entity.setCode(model.getCode()); 
			entity.setName(model.getName()); 
	
			return entity; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	}  
}
