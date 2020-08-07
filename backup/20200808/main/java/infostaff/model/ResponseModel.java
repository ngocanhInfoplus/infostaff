package infostaff.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel {
	
	private Date timestamp;
	private String respCode;
	private String respMessage;
	
	public ResponseModel() {
		super();
	}
	
	public ResponseModel(String respCode, String respMessage) {
		this.respCode = respCode;
		this.respMessage = respMessage;
	}
	
	public ResponseModel(Date timeStamp, String respCode, String respMessage) {
		this.timestamp = timeStamp;
		this.respCode = respCode;
		this.respMessage = respMessage;
	}
	
	
}
