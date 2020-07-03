package infostaff.mapping; 

import org.springframework.stereotype.Component;

import infostaff.entity.TblGroupEntity;
import infostaff.model.GroupModel;
import lombok.extern.slf4j.Slf4j; 


@Slf4j 
@Component 
public class GroupMapping{ 
	public GroupModel entityToModel(TblGroupEntity entity){ 
	
		GroupModel model  = new GroupModel(); 
		try{ 
			model.setGroupId(entity.getGroupId()); 
			model.setGroupName(entity.getGroupName()); 
			model.setDepartmentId(entity.getDepartmentId()); 
			model.setCreatedUser(entity.getCreatedUser()); 
			model.setCreatedDate(entity.getCreatedDate()); 
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	public TblGroupEntity modelToEntity(GroupModel model){ 
	
		TblGroupEntity entity  = new TblGroupEntity(); 
		try{ 
			entity.setGroupId(model.getGroupId()); 
			entity.setGroupName(model.getGroupName()); 
			entity.setDepartmentId(model.getDepartmentId()); 
			entity.setCreatedUser(model.getCreatedUser()); 
			entity.setCreatedDate(model.getCreatedDate()); 
	
			return entity; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
}