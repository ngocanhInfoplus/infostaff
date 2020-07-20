package infostaff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblRoleEntity;
import infostaff.entity.TblUserRoleEntity;

@Repository
public interface TblUserRoleRepository extends JpaRepository<TblUserRoleEntity, Long>{
		
	@Query("SELECT a.tblRoleEntity.roleName FROM TblUserRoleEntity a WHERE a.tblUserEntity.userName = :username")
	List<String> getRoleNames(@Param("username") String username);
	
	@Query("SELECT a.tblRoleEntity FROM TblUserRoleEntity a WHERE a.tblUserEntity.userName = :username")
	TblRoleEntity getUserRole(@Param("username") String username);
	
}
