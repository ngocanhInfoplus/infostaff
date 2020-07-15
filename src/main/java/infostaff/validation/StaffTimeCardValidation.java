package infostaff.validation;

import infostaff.model.StaffTimeCardModel;
import org.apache.commons.lang3.StringUtils;

public class StaffTimeCardValidation {

    public boolean cTCValid(StaffTimeCardModel model) {
        if(StringUtils.isNotBlank(model.getStaffId().toString()) || StringUtils.isNotBlank(model.getCheckOut()) || StringUtils.isNotBlank(model.getWorkingDate().toString()))
            return true;
        return false;
    }

    public boolean cOValid(StaffTimeCardModel model) {
        if(StringUtils.isNotBlank(model.getStaffId().toString()) || StringUtils.isNotBlank(model.getCheckOut()) || StringUtils.isNotBlank(model.getWorkingDate().toString()))
            return true;
        return false;
    }

    public boolean cIValid(StaffTimeCardModel model) {
    	if(model.getStaffId() == null)
    		return false;
    	return true;
    }
}
