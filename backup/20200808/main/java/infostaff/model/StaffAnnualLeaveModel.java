package infostaff.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StaffAnnualLeaveModel {
	
	private Long staffId;
	private Float usedAnnualLeave;
	private Float abilityAnnualLeave;
	private Float totalAnnualLeave;
	
	public StaffAnnualLeaveModel(Long staffId, Float usedAnnalLeave, Float totalAnnualLeave) {
		this.staffId = staffId;
		this.usedAnnualLeave = usedAnnalLeave;
		this.totalAnnualLeave = totalAnnualLeave;
		this.abilityAnnualLeave = totalAnnualLeave - usedAnnalLeave;
	}

}
