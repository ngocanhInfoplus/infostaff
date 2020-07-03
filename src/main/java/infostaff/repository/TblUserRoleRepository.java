package infostaff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblUserRoleEntity;

@Repository
public interface TblUserRoleRepository extends JpaRepository<TblUserRoleEntity, Long>{
		
	@Query("SELECT a.tblRoleEntity.roleName FROM TblUserRoleEntity a WHERE a.tblUserEntity.userId = :userId")
	List<String> getRoleNames(@Param("userId") Long userId);
	
}
