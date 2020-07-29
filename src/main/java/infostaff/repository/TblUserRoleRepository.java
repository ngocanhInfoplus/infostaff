package infostaff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblRoleEntity;
import infostaff.entity.TblUserRoleEntity;

import javax.transaction.Transactional;

@Repository
public interface TblUserRoleRepository extends JpaRepository<TblUserRoleEntity, Long>{
		
	@Query("SELECT a.tblRoleEntity.roleName FROM TblUserRoleEntity a WHERE a.tblUserEntity.userName = :username")
	List<String> getRoleNames(@Param("username") String username);

	@Transactional
	@Modifying
	@Query("delete FROM TblUserRoleEntity a WHERE a.tblUserEntity.userName = :username")
	void deleteUserRoleByUserName(@Param("username") String username);

	
}
