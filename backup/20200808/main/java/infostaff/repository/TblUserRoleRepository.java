package infostaff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import infostaff.entity.TblRoleEntity;
import infostaff.entity.TblUserRoleEntity;

@Repository
public interface TblUserRoleRepository extends JpaRepository<TblUserRoleEntity, Long>{
		
	@Query("SELECT a.tblRoleEntity.roleName FROM TblUserRoleEntity a WHERE a.tblUserEntity.userName = :username")
	List<String> getRoleNames(@Param("username") String username);
	
	@Query("SELECT a.tblRoleEntity FROM TblUserRoleEntity a WHERE a.tblUserEntity.userName = :username")
	TblRoleEntity getUserRole(@Param("username") String username);
	
	@Query("SELECT a FROM TblUserRoleEntity a WHERE a.tblUserEntity.userName = :username")
	List<TblUserRoleEntity> getByUserName(@Param("username") String username);
	
	@Modifying
	@Transactional
	@Query("DELETE TblUserRoleEntity a WHERE a.tblUserEntity.userName = :username ")
	void deleteByUserName(@Param("username") String username);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO tbluserrole (user_name, role_id) "
			+ "VALUES(:username, :roleId) ", nativeQuery = true)
	void insert(@Param("username") String username, @Param("roleId") Long roleId);
	
}
