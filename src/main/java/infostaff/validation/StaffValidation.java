package infostaff.validation; 

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import infostaff.model.StaffModel; 


@Component 
public class StaffValidation{ 
	public boolean validate(StaffModel model){ 
	
			if(StringUtils.isEmpty(model.getStaffName())) 
				return false; 
			if(model.getJoinDate() == null) 
				return false; 
			if(model.getDob() == null) 
				return false; 
			if(StringUtils.isEmpty(model.getGender())) 
				return false; 
			if(StringUtils.isEmpty(model.getLocationCode())) 
				return false; 
			if(StringUtils.isEmpty(model.getJobTitleCode()))
				return false;
			if(StringUtils.isEmpty(model.getDepartmentCode()))
				return false;
			if(StringUtils.isEmpty(model.getGroupCode()))
				return false;
			if(StringUtils.isEmpty(model.getCountryCode()))
				return false;
			if(StringUtils.isEmpty(model.getAddress01()))
				return false;
	
			return true; 
	} 
}