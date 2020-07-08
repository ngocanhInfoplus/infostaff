package infostaff.common;

import infostaff.model.ResponseModel;

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
}