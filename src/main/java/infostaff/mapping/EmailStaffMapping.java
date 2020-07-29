package infostaff.mapping;

import org.springframework.stereotype.Component;

import infostaff.entity.TblEmailStaffEntity;
import infostaff.model.EmailStaffModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Component 
public class EmailStaffMapping {
	
	public EmailStaffModel entityToModel(TblEmailStaffEntity entity){ 
		
		EmailStaffModel model  = new EmailStaffModel(); 
		try{ 
			model.setId(entity.getId()); 
//			model.setEmailTempCode(entity.getEmailTempCode()); 
//			model.setStaffId(entity.getStaffId()); 
			model.setCreatedUser(entity.getCreatedUser());
			model.setCreatedDate(entity.getCreatedDate()); 
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	public TblEmailStaffEntity modelToEntity(EmailStaffModel model){ 
	
		TblEmailStaffEntity entity  = new TblEmailStaffEntity(); 
		try{ 
			entity.setId(model.getId()); 
//			entity.setEmailTempCode(model.getEmailTempCode()); 
//			entity.setStaffId(model.getStaffId()); 
			entity.setCreatedUser(model.getCreatedUser());
			entity.setCreatedDate(model.getCreatedDate()); 
	
			return entity; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 

}
