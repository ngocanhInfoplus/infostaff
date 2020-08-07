package infostaff.mapping; 

import org.springframework.stereotype.Component;

import infostaff.entity.TblRoleEntity;
import infostaff.entity.TblUserEntity;
import infostaff.entity.TblUserRoleEntity;
import infostaff.model.UserRoleModel;
import lombok.extern.slf4j.Slf4j; 


@Slf4j 
@Component 
public class UserRoleMapping{ 
	public UserRoleModel entityToModel(TblUserRoleEntity entity){ 
	
		UserRoleModel model  = new UserRoleModel(); 
		try{ 
			model.setId(entity.getId()); 
			model.setUserName(entity.getTblUserEntity().getUserName()); 
			model.setRoleId(entity.getTblRoleEntity().getRoleId()); 
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	public TblUserRoleEntity modelToEntity(UserRoleModel model){ 
	
		TblUserRoleEntity entity  = new TblUserRoleEntity();
		TblUserEntity userEntity = new TblUserEntity();
		TblRoleEntity roleEntity = new TblRoleEntity();
		try{
			userEntity.setUserName(model.getUserName());
			roleEntity.setRoleId(model.getRoleId());
			
			entity.setId(model.getId()); 			
			entity.setTblUserEntity(userEntity);
			entity.setTblRoleEntity(roleEntity);

			return entity; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
}