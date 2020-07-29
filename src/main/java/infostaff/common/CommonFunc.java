package infostaff.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import infostaff.model.ResponseModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonFunc {

	public static ResponseModel createResponseModelByCode(String code) {

		switch (code) {
		case CommonParam.CODE_SUCCESS:
			return new ResponseModel(code, "Execute successed");
		case CommonParam.CODE_FAILED:
			return new ResponseModel(code, "Execute database failed");
		case CommonParam.CODE_DUPLICATED:
			return new ResponseModel(code, "Duplicated data");
		case CommonParam.CODE_NO_DATA_FOUND:
			return new ResponseModel(code, "No data found");
		case CommonParam.CODE_CONVERT_ERROR:
			return new ResponseModel(code, "Convert data error");
		case CommonParam.CODE_VALIDATION_ERROR:
			return new ResponseModel(code, "Validation error");
		default:
			return new ResponseModel();
		}
	}
	
	public static String dateToString(Date date, String format) {
		if (date == null)
			return StringUtils.EMPTY;

		return new SimpleDateFormat(format).format(date);
	}
	
	public static int hoursDifference(Date date1, Date date2) {

	    final int MILLI_TO_HOUR = 1000 * 60 * 60;
	    return (int) (date1.getTime() - date2.getTime()) / MILLI_TO_HOUR;
	}
	
	public static String getRoleName(User user) {
		if (user == null)
			return StringUtils.EMPTY;
		log.info("User name: " + user.getUsername());

		// get main menu
		String roleName = StringUtils.EMPTY;
		Iterator<GrantedAuthority> grantedAuthority = user.getAuthorities().iterator();

		if (grantedAuthority.hasNext())
			roleName = grantedAuthority.next().toString();
		log.info("Role name: " + roleName);
		
		return roleName;
	}
}
