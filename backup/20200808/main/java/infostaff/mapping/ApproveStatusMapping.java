package infostaff.mapping;

import infostaff.entity.TblApproveStatusEntity;
import infostaff.model.ApproveStatusModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
public class ApproveStatusMapping {
	public ApproveStatusModel entityToModel(TblApproveStatusEntity entity){ 
		
		ApproveStatusModel model  = new ApproveStatusModel(); 
		try{ 
			model.setCode(entity.getCode()); 
			model.setName(entity.getName()); 
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	public TblApproveStatusEntity modelToEntity(ApproveStatusModel model){ 
	
		TblApproveStatusEntity entity  = new TblApproveStatusEntity(); 
		try{ 
			entity.setCode(model.getCode()); 
			entity.setName(model.getName()); 
	
			return entity; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
}
