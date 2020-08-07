package infostaff.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import infostaff.model.StaffOvertimeModel;

@Component
public class StaffOvertimeValidation {
	public boolean validate(StaffOvertimeModel model) {

		if (model == null)
			return false;

		if (model.getRegisterDate() == null)
			return false;
		
		if (model.getFromTime() == null)
			return false;

		if (model.getToTime() == null)
			return false;

		if (model.getOtType() == null || StringUtils.isEmpty(model.getOtType().getCode()))
			return false;

		return true;
	}
}