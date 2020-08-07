package infostaff.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import infostaff.model.OvertimeTypeModel;

@Component
public class OvertimeTypeValidation {

	public boolean validate(OvertimeTypeModel model) {

		if(model == null)
			return false;
		
		if (StringUtils.isEmpty(model.getCode()))
			return false;
		
		if (model.getName() == null)
			return false;

		return true;
	}
}