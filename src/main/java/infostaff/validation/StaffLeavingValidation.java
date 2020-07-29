package infostaff.validation;

import org.apache.commons.lang3.StringUtils;

import infostaff.common.CommonParam;
import infostaff.model.StaffLeavingModel;

public class StaffLeavingValidation {
	
	public boolean validate(StaffLeavingModel model) {

		if(model == null)
			return false;
		
		if (model.getStaffId() == null)
			return false;

		if(StringUtils.isEmpty(model.getLeavingType()))
			return false;
		
		if(StringUtils.isEmpty(model.getTimeOffType()))
			return false;
		
		if(model.getFromDate() == null)
			return false;
		
		if(StringUtils.isEmpty(model.getReason()))
			return false;
		
		if(model.getManagerId01() == null)
			return false;
		
		if(StringUtils.isEmpty(model.getApproveStatus()))
			return false;
		
		if(CommonParam.TIMEOFF_OTHER.equals(model.getTimeOffType())) {
			if(model.getFromDate() == null || model.getToDate() == null)
				return false;
		
			if(model.getToDate().compareTo(model.getFromDate()) <= 0)
				return false;
		}
				
		return true;
	}
}
