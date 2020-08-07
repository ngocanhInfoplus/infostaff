package infostaff.mapping;

import org.springframework.stereotype.Component;

import infostaff.entity.TblUserEntity;
import infostaff.model.UserModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Component 
public class UserMapping {
	
	public UserModel entityToModel(TblUserEntity entity){ 
		
		UserModel model  = new UserModel(); 
		try{ 
			model.setUserName(entity.getUserName());
			model.setEncrytedPassword(entity.getEncrytedPassword());
			model.setEnabled(entity.isEnabled());
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	public TblUserEntity modelToEntity(UserModel model){ 
	
		TblUserEntity entity  = new TblUserEntity(); 
		try{ 
			entity.setUserName(model.getUserName());
			entity.setEncrytedPassword(model.getEncrytedPassword());
			entity.setEnabled(model.isEnabled());
	
			return entity; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 

}
