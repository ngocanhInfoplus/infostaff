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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter; 


@Getter 
@Setter 
@Entity 
@Table(name = "tbluserrole") 
 public class TblUserRoleEntity implements Serializable{ 
	
	private static final long serialVersionUID = 1L; 

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false) 
	private Long id; 
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_name")
    private TblUserEntity tblUserEntity;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private TblRoleEntity tblRoleEntity;
	
	@Column(name = "created_user", length = 10)
	private String createdUser;
	
	@Column(name = "created_date")
	private Date createdDate;
}