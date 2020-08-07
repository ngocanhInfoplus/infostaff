package infostaff.mapping; 

import org.springframework.stereotype.Component;

import infostaff.entity.TblJobTitleEntity;
import infostaff.model.JobTitleModel;
import lombok.extern.slf4j.Slf4j; 


@Slf4j 
@Component 
public class JobTitleMapping{ 
	public JobTitleModel entityToModel(TblJobTitleEntity entity){ 
	
		JobTitleModel model  = new JobTitleModel(); 
		try{ 
			model.setCode(entity.getCode()); 
			model.setName(entity.getName()); 
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	public TblJobTitleEntity modelToEntity(JobTitleModel model){ 
	
		TblJobTitleEntity entity  = new TblJobTitleEntity(); 
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