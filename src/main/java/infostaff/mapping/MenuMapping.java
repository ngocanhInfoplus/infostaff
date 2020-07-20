package infostaff.mapping;

import infostaff.entity.TblMenuEntity;
import infostaff.model.MenuModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
public class MenuMapping {
	public MenuModel entityToModel(TblMenuEntity entity){ 
		
		MenuModel model  = new MenuModel(); 
		try{ 
			model.setMenuId(entity.getMenuId()); 
			model.setMenuName(entity.getMenuName()); 
			model.setIcon(entity.getIcon()); 
			model.setRouter(entity.getRouter()); 
			model.setCreatedUser(entity.getCreatedUser()); 
			model.setCreatedDate(entity.getCreatedDate()); 
			model.setChangedUser(entity.getChangedUser()); 
			model.setChangedDate(entity.getChangedDate()); 
			model.setRecordStatus(entity.getRecordStatus()); 
			model.setOrderBy(entity.getOrderBy()); 
			model.setParentMenuId(entity.getParentMenuId()); 
			model.setGroupId(entity.getGroupId()); 
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	
	public TblMenuEntity modelToEntity(MenuModel model){ 
	
		TblMenuEntity entity  = new TblMenuEntity(); 
		try{ 
			entity.setMenuId(model.getMenuId()); 
			entity.setMenuName(model.getMenuName()); 
			entity.setIcon(model.getIcon()); 
			entity.setRouter(model.getRouter()); 
			entity.setCreatedUser(model.getCreatedUser()); 
			entity.setCreatedDate(model.getCreatedDate()); 
			entity.setChangedUser(model.getChangedUser()); 
			entity.setChangedDate(model.getChangedDate()); 
			entity.setRecordStatus(model.getRecordStatus()); 
			entity.setOrderBy(model.getOrderBy()); 
			entity.setParentMenuId(model.getParentMenuId()); 
			entity.setGroupId(model.getGroupId()); 
	
			return entity; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
}
