package infostaff.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Entity 
@Table(name = "tblemailtemplate")
public class TblEmailTemplateEntity implements Serializable{ 
	private static final long serialVersionUID = 1L; 

	@Id 
	@Column(name = "template_code", nullable = false, length = 10) 
	private String templateCode; 

	@Column(name = "template_content", nullable = false, length = 100) 
	private String templateContent; 

	@Column(name = "default_id_recv_01") 
	private Long defaultIdRecv01; 

	@Column(name = "default_id_recv_02") 
	private Long defaultIdRecv02; 

	@Column(name = "default_id_recv_03") 
	private Long defaultIdRecv03; 

	@Column(name = "default_id_recv_04") 
	private Long defaultIdRecv04; 

	@Column(name = "default_id_recv_05") 
	private Long defaultIdRecv05; 

	@Column(name = "created_user", length = 10) 
	private String createdUser; 

	@Column(name = "created_date") 
	private Date createdDate; 

	@Column(name = "record_status", length = 1) 
	private String recordStatus; 

}