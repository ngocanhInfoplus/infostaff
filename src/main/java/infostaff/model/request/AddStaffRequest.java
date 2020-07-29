package infostaff.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class AddStaffRequest {

    private String staffCode;
    private String staffName;
    private Date joinDate;
    private Date dob;
    private String gender;
    private String locationCode;
    private String fileName;
    private String email;
    private String userName;

}
