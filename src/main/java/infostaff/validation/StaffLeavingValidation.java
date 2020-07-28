package infostaff.validation;

import infostaff.model.RoleModel;
import infostaff.model.StaffLeavingModel;
import org.apache.commons.lang3.StringUtils;

public class StaffLeavingValidation {
    public boolean validate(StaffLeavingModel model) {

        if(model == null)
            return false;
        if (StringUtils.isEmpty(model.getReason()))
            return false;

        return true;
    }
}
