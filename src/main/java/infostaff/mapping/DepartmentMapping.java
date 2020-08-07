package infostaff.mapping; 

import java.util.List;

import org.springframework.stereotype.Component;

import infostaff.entity.TblDepartmentEntity;
import infostaff.model.DepartmentModel;
import infostaff.model.GroupModel;
import lombok.extern.slf4j.Slf4j; 


@Slf4j 
@Component 
public class DepartmentMapping{ 
	public DepartmentModel entityToModel(TblDepartmentEntity entity){ 
	
		DepartmentModel model  = new DepartmentModel(); 
		try{ 
			
			model.setDepartmentCode(entity.getDepartmentCode());
			model.setDepartmentName(entity.getDepartmentName());
			
			GroupMapping groupMapping = new GroupMapping();
			List<GroupModel> groupModels = groupMapping.entitiesToModels(entity.getGroups());
			model.setGroups(groupModels);
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	public TblDepartmentEntity modelToEntity(DepartmentModel model){ 
	
		TblDepartmentEntity entity  = new TblDepartmentEntity(); 
		try{ 
			entity.setDepartmentCode(model.getDepartmentCode());
			entity.setDepartmentName(model.getDepartmentName());
	
			return entity; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
}