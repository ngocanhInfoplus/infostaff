package infostaff.validation;

import org.apache.commons.lang3.StringUtils;

import infostaff.model.MenuModel;

public class MenuValidation {

	public boolean validate(MenuModel model) {

		if(model == null)
			return false;
		
		if (StringUtils.isEmpty(model.getMenuName()))
			return false;

		return true;
	} 
}
