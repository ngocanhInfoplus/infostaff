package infostaff.mapping; 

import org.springframework.stereotype.Component;

import infostaff.entity.TblDepartmentEntity;
import infostaff.model.DepartmentModel;
import lombok.extern.slf4j.Slf4j; 


@Slf4j 
@Component 
public class DepartmentMapping{ 
	public DepartmentModel entityToModel(TblDepartmentEntity entity){ 
	
		DepartmentModel model  = new DepartmentModel(); 
		try{ 
			model.setDeparmentId(entity.getDeparmentId()); 
			model.setDeparmentName(entity.getDeparmentName()); 
			model.setCreatedUser(entity.getCreatedUser()); 
			model.setCreatedDate(entity.getCreatedDate()); 
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	public TblDepartmentEntity modelToEntity(DepartmentModel model){ 
	
		TblDepartmentEntity entity  = new TblDepartmentEntity(); 
		try{ 
			entity.setDeparmentId(model.getDeparmentId()); 
			entity.setDeparmentName(model.getDeparmentName()); 
			entity.setCreatedUser(model.getCreatedUser()); 
			entity.setCreatedDate(model.getCreatedDate()); 
	
			return entity; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
}