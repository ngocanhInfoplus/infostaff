package infostaff.mapping; 

import org.springframework.stereotype.Component;

import infostaff.entity.TblCountryEntity;
import infostaff.model.CountryModel;
import lombok.extern.slf4j.Slf4j; 


@Slf4j 
@Component 
public class CountryMapping{ 
	public CountryModel entityToModel(TblCountryEntity entity){ 
	
		CountryModel model  = new CountryModel(); 
		try{ 
			model.setCode(entity.getCode()); 
			model.setName(entity.getName()); 
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	
	public TblCountryEntity modelToEntity(CountryModel model){ 
	
		TblCountryEntity entity  = new TblCountryEntity(); 
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