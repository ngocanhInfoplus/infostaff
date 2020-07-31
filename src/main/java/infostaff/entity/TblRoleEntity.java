package infostaff.entity; 

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter 
@Setter 
@Entity 
@Table(name = "tblrole")
 public class TblRoleEntity implements Serializable{ 
	private static final long serialVersionUID = 1L; 

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", nullable = false) 
	private Long roleId; 

	@Column(name = "role_name", nullable = false, length = 100) 
	private String roleName;

	@ManyToMany(mappedBy = "roles")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Collection<TblUserEntity> users;

}