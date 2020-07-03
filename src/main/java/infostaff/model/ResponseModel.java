package infostaff.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel {
	
	private String respCode;
	private String respMessage;
	
	public ResponseModel() {
		super();
	}
	
	public ResponseModel(String respCode, String respMessage) {
		this.respCode = respCode;
		this.respMessage = respMessage;
	}
}
