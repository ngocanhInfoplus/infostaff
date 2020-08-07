package infostaff.entity; 

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter; 


@Getter 
@Setter 
@Entity 
@Table(name = "tblstaff")
 public class TblStaffEntity implements Serializable{ 
	private static final long serialVersionUID = 1L; 

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id", nullable = false, length = 11) 
	private Long staffId; 

	@Column(name = "staff_code", nullable = false, length = 20) 
	private String staffCode; 

	@Column(name = "staff_name", nullable = false, length = 100) 
	private String staffName; 

	@Column(name = "join_date", nullable = false) 
	private Date joinDate; 

	@Column(name = "dob", nullable = false) 
	private Date dob; 

	@Column(name = "gender", nullable = false, length = 1) 
	private String gender; 

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_code")
	private TblLocationEntity locationEntity;
//	@Column(name = "location_code", nullable = false, length = 10) 
//	private String locationCode; 

	@Column(name = "file_name", length = 100) 
	private String fileName; 

	@Column(name = "created_user", length = 10) 
	private String createdUser; 

	@Column(name = "created_date") 
	private Date createdDate; 

	@Column(name = "changed_user", length = 10) 
	private String changedUser; 

	@Column(name = "changed_date") 
	private Date changedDate; 

	@Column(name = "record_status", length = 1) 
	private String recordStatus; 
	
	@Column(name = "manager_id") 
	private Long managerId; 
	
	@Column(name = "email", length = 100) 
	private String email; 
	
	@Column(name="user_name", length = 20)
	private String userName;
	
	@Column(name="advance_annual_leave")
	private Float advanceAnnualLeave;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_code")
	private TblGroupEntity groupEntity;
//	@Column(name="group_code", length = 10)
//	private String groupCode;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_title_code")
	private TblJobTitleEntity jobTitleEntity;
	
	@Column(name="address_01", length = 200)
	private String address01;
	
	@Column(name="address_02", length = 200)
	private String address02;
	
	@Column(name="phone_no", length = 20)
	private String phoneNo;
	
	@Column(name="identity_card_no", length = 50)
	private String identityCardNo;
	
	@Column(name="identity_card_date")
	private Date identityCardDate;
	
	@Column(name="identity_card_location", length = 200)
	private String identityCardLocation;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_code")
	private TblCountryEntity countryEntity;
	
	@Column(name="visa_type", length = 10)
	private String visaType;
	
	@Column(name="visa_no", length = 50)
	private String visaNo;
	
	@Column(name="involed_name", length = 100)
	private String involedName;
	
	@Column(name="involed_phone", length = 20)
	private String involedPhone;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_code")
	private TblDepartmentEntity departmentEntity;

}