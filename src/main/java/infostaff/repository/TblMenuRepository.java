package infostaff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblMenuEntity;

@Repository
public interface TblMenuRepository extends JpaRepository<TblMenuEntity, Long>{

	@Query("SELECT a.tblMenuEntity FROM TblRoleMenuEntity a WHERE a.tblRoleEntity.roleId = :roleId"
			+ " ORDER BY a.tblMenuEntity.groupId, a.tblMenuEntity.orderBy")
	List<TblMenuEntity> findMenusByRoleId(@Param("roleId") Long roleId);
	
	@Query("SELECT a.tblMenuEntity FROM TblRoleMenuEntity a "
			+ "WHERE a.tblRoleEntity.roleName = :roleName "
			+ "AND a.tblMenuEntity.parentMenuId = :parentMenuId "
			+ "AND a.tblMenuEntity.recordStatus = 'O' "
			+ "ORDER BY a.tblMenuEntity.menuId")
	List<TblMenuEntity> findMenusByRoleNameAndParentId(@Param("roleName") String roleName, @Param("parentMenuId") Long parentMenuId);
	
	
	@Query("SELECT a FROM TblMenuEntity a "
			+ "WHERE a.recordStatus = :recordStatus"
			+ " ORDER BY a.groupId, a.orderBy")
	List<TblMenuEntity> findAllMenu(String recordStatus);
	
	@Query("SELECT a FROM TblMenuEntity a "
			+ "WHERE a.recordStatus = :recordStatus "
			+ "AND a.parentMenuId = '0'"
			+ "ORDER BY a.groupId, a.orderBy")
	List<TblMenuEntity> findAllParentMenu(String recordStatus);
}
