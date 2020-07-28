package infostaff.model.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StaffLeavingRequest {

    @NotBlank
    private String staffId;

    @NotBlank
    private String leavingType;

    @NotBlank
    private String fromDate;

    @NotBlank
    private String toDate;

    @Min(1)
    private int totalHour;

    @NotBlank
    private String reason;

    private String sendType;

    @NotNull
    private Long managerId01;

    @NotNull
    private Long managerId02;

}
