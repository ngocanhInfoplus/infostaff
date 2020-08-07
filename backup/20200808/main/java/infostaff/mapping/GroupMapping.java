package infostaff.mapping; 

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import infostaff.entity.TblDepartmentEntity;
import infostaff.entity.TblGroupEntity;
import infostaff.model.GroupModel;
import lombok.extern.slf4j.Slf4j; 


@Slf4j 
@Component 
public class GroupMapping{ 
	public GroupModel entityToModel(TblGroupEntity entity){ 
	
		GroupModel model  = new GroupModel(); 
		try{ 
			model.setGroupCode(entity.getGroupCode());
			model.setGroupName(entity.getGroupName());
			model.setDepartmentCode(entity.getDepartmentEntity().getDepartmentCode());
			model.setDepartmentName(entity.getDepartmentEntity().getDepartmentName());
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	public TblGroupEntity modelToEntity(GroupModel model){ 
	
		TblGroupEntity entity  = new TblGroupEntity(); 
		try{ 
			entity.setGroupCode(model.getGroupCode());
			entity.setGroupName(model.getGroupName());
			
			TblDepartmentEntity departmentEntity = new TblDepartmentEntity();
			departmentEntity.setDepartmentCode(model.getDepartmentCode());
			departmentEntity.setDepartmentName(model.getDepartmentName());
			entity.setDepartmentEntity(departmentEntity);
	
			return entity; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	
	public List<GroupModel> entitiesToModels(List<TblGroupEntity> entities){
		
		if(entities == null)
			return null;
		
		List<GroupModel> models = new ArrayList<GroupModel>();
		
		for(TblGroupEntity entity: entities) {
			GroupModel model = entityToModel(entity);
			
			if(model != null)
				models.add(model);
				
		}
		return models;
			
	}
}