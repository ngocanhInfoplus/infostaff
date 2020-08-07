package infostaff.validation;

import org.apache.commons.lang3.StringUtils;

import infostaff.model.StaffTimeCardModel;

public class StaffTimeCardValidation {

    public boolean cTCValid(StaffTimeCardModel model) {
        if(model.getStaffId() == null 
        		|| StringUtils.isNotBlank(model.getCheckOut()) 
        		|| model.getWorkingDate() == null)
            return true;
        return false;
    }

    public boolean cOValid(StaffTimeCardModel model) {
        if(model.getStaffId() == null 
        		|| StringUtils.isNotBlank(model.getCheckOut()) 
        		|| model.getWorkingDate() == null)
            return true;
        return false;
    }

    public boolean cIValid(StaffTimeCardModel model) {
    	if(model.getStaffId() == null)
    		return false;
    	return true;
    }
}
