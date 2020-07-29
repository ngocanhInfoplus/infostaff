package infostaff.mapping;

import infostaff.entity.TblEmailTemplateEntity;
import infostaff.model.EmailTemplateModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailTemplateMapping {

	public EmailTemplateModel entityToModel(TblEmailTemplateEntity entity){
		
		EmailTemplateModel model  = new EmailTemplateModel();
		try{ 
			model.setTemplateCode(entity.getTemplateCode()); 
			model.setTemplateContent(entity.getTemplateContent()); 
			model.setCreatedUser(entity.getCreatedUser()); 
			model.setCreatedDate(entity.getCreatedDate()); 
			model.setRecordStatus(entity.getRecordStatus()); 
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	public TblEmailTemplateEntity modelToEntity(EmailTemplateModel model){
	
		TblEmailTemplateEntity entity  = new TblEmailTemplateEntity(); 
		try{ 
			entity.setTemplateCode(model.getTemplateCode()); 
			entity.setTemplateContent(model.getTemplateContent()); 
			entity.setCreatedUser(model.getCreatedUser()); 
			entity.setCreatedDate(model.getCreatedDate()); 
			entity.setRecordStatus(model.getRecordStatus()); 
	
			return entity; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 

}
