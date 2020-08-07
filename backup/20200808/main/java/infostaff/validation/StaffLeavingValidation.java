package infostaff.validation;

import org.apache.commons.lang3.StringUtils;

import infostaff.common.CommonParam;
import infostaff.model.StaffLeavingModel;

public class StaffLeavingValidation {
	
	public boolean validate(StaffLeavingModel model) {

		if(model == null)
			return false;
		
//		if (model.getStaffId() == null)
//			return false;

		if(StringUtils.isEmpty(model.getLeavingTypeCode()))
			return false;
		
		if(StringUtils.isEmpty(model.getTimeOffCode()))
			return false;
		
		if(model.getFromDate() == null)
			return false;
		
		if(StringUtils.isEmpty(model.getReason()))
			return false;
		
		if(model.getManagerId01() == null)
			return false;
		
//		if(StringUtils.isEmpty(model.getApproveStatus()))
//			return false;
		
		if(CommonParam.TIMEOFF_MORE.equals(model.getTimeOffCode())) {
			if(model.getFromDate() == null || model.getToDate() == null)
				return false;
		
			if(model.getToDate().compareTo(model.getFromDate()) <= 0)
				return false;
		}
				
		return true;
	}
}
