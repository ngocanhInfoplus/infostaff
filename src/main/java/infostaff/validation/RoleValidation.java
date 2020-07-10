package infostaff.validation;

import org.apache.commons.lang3.StringUtils;

import infostaff.model.RoleModel;

public class RoleValidation {
	
	public boolean validate(RoleModel model){ 
		
		if(StringUtils.isEmpty(model.getRoleName())) 
			return false; 

		return true; 
} 
}
