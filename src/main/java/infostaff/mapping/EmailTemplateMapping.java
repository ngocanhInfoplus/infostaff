package infostaff.mapping;

import infostaff.entity.TblEmailTemplateEntity;
import infostaff.model.TblEmailTemplateModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailTemplateMapping {

	public TblEmailTemplateModel entityToModel(TblEmailTemplateEntity entity){ 
		
		TblEmailTemplateModel model  = new TblEmailTemplateModel(); 
		try{ 
			model.setTemplateCode(entity.getTemplateCode()); 
			model.setTemplateContent(entity.getTemplateContent()); 
			model.setDefaultIdRecv01(entity.getDefaultIdRecv01()); 
			model.setDefaultIdRecv02(entity.getDefaultIdRecv02()); 
			model.setDefaultIdRecv03(entity.getDefaultIdRecv03()); 
			model.setDefaultIdRecv04(entity.getDefaultIdRecv04()); 
			model.setDefaultIdRecv05(entity.getDefaultIdRecv05()); 
			model.setCreatedUser(entity.getCreatedUser()); 
			model.setCreatedDate(entity.getCreatedDate()); 
			model.setRecordStatus(entity.getRecordStatus()); 
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	public TblEmailTemplateEntity modelToEntity(TblEmailTemplateModel model){ 
	
		TblEmailTemplateEntity entity  = new TblEmailTemplateEntity(); 
		try{ 
			entity.setTemplateCode(model.getTemplateCode()); 
			entity.setTemplateContent(model.getTemplateContent()); 
			entity.setDefaultIdRecv01(model.getDefaultIdRecv01()); 
			entity.setDefaultIdRecv02(model.getDefaultIdRecv02()); 
			entity.setDefaultIdRecv03(model.getDefaultIdRecv03()); 
			entity.setDefaultIdRecv04(model.getDefaultIdRecv04()); 
			entity.setDefaultIdRecv05(model.getDefaultIdRecv05()); 
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
