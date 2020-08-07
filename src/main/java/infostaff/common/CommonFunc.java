package infostaff.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import infostaff.entity.TblStaffEntity;
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

	public static float daysDifference(Date toDate, Date fromDate) {
		float daysdiff = 0;
		long diff = toDate.getTime() - fromDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
		daysdiff = (float) diffDays;
		return daysdiff;
	}

	public static float hoursDifference(Date toDate, Date fromDate, boolean restTime) {

		final float MILLI_TO_HOUR = 1000 * 60 * 60;

		if (restTime)
			return (float) ((toDate.getTime() - fromDate.getTime()) / MILLI_TO_HOUR - CommonParam.DEFAULT_REST_TIME);
		else
			return (float) (toDate.getTime() - fromDate.getTime()) / MILLI_TO_HOUR;
	}

	public static Date getDateTime(String strDateTime, String type) {
		SimpleDateFormat format = new SimpleDateFormat(type);

		try {
			return format.parse(strDateTime);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String getTimeString(Date dDate, String type) {
		SimpleDateFormat format = new SimpleDateFormat(type);
		return format.format(dDate);
	}

	public static String getMangerIdsString(Long id1, Long id2) {

		if (id1 == null && id2 == null)
			return StringUtils.EMPTY;

		String str = StringUtils.EMPTY;

		if (id1 != null)
			str += id1.toString();

		if (id2 != null)
			str += "," + id2.toString();
		
		return str;

	}
	
	public static String getEmailString(List<TblStaffEntity> entities) {

		if (entities == null || entities.isEmpty())
			return StringUtils.EMPTY;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < entities.size(); i++) {

			if (i == entities.size() - 1)
				sb.append(entities.get(i).getEmail());
			else
				sb.append(entities.get(i).getEmail() + ",");
		}
		return sb.toString();
	}
	
	public static String getApproveStatusName(String approveStatusCode) {
		switch (approveStatusCode) {
		case "I":
			return "Init";
		case "W":
			return "Waiting";
		case "A":
			return "Approved";
		case "D":
			return "Denied";
		default:
			return "Init";
		}
	}
}
