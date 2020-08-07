package infostaff.mapping; 

import org.springframework.stereotype.Component;

import infostaff.entity.TblLocationEntity;
import infostaff.model.LocationModel;
import lombok.extern.slf4j.Slf4j; 


@Slf4j 
@Component 
public class LocationMapping{ 
	public LocationModel entityToModel(TblLocationEntity entity){ 
	
		LocationModel model  = new LocationModel(); 
		try{ 
			model.setCode(entity.getCode()); 
			model.setName(entity.getName()); 
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	public TblLocationEntity modelToEntity(LocationModel model){ 
	
		TblLocationEntity entity  = new TblLocationEntity(); 
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