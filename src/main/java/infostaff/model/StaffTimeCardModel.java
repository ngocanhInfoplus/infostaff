package infostaff.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StaffTimeCardModel {

    private Long id;

    private Long staffId;

    private Date workingDate;

    private String checkIn;

    private String checkOut;

    private boolean isChecked;

    private String note;

    private String fileName;

    private String createdUser;

    private Date createdDate;

    private String changedUser;

    private Date changedDate;

    private String recordStatus;

    // for search
    private Date fromDate;

    private Date toDate;

    private Long groupId;

    private String staffCode;

    private String staffName;
    //end search

}