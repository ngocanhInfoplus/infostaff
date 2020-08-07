package infostaff.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tblmenu")
public class TblMenuEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menu_id", nullable = false, length = 11)
	private Long menuId;

	@Column(name = "menu_name", nullable = false, length = 45)
	private String menuName;

	@Column(name = "icon", length = 45)
	private String icon;

	@Column(name = "router", length = 45)
	private String router;

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

	@Column(name = "order_by", length = 10)
	private Integer orderBy;

	@Column(name = "parent_menu_id", length = 11)
	private Long parentMenuId;

	@Column(name = "group_id", length = 10)
	private Long groupId;

}
