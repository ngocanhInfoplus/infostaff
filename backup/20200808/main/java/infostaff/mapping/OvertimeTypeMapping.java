package infostaff.mapping; 

import org.springframework.stereotype.Component;

import infostaff.entity.TblOvertimeTypeEntity;
import infostaff.model.OvertimeTypeModel;
import lombok.extern.slf4j.Slf4j; 


@Slf4j 
@Component 
public class OvertimeTypeMapping{ 
	public OvertimeTypeModel entityToModel(TblOvertimeTypeEntity entity){ 
	
		OvertimeTypeModel model  = new OvertimeTypeModel(); 
		try{ 
			model.setCode(entity.getCode()); 
			model.setName(entity.getName()); 
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	public TblOvertimeTypeEntity modelToEntity(OvertimeTypeModel model){ 
	
		TblOvertimeTypeEntity entity  = new TblOvertimeTypeEntity(); 
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