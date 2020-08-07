package infostaff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblGroupEntity;

@Repository
public interface TblGroupRepository  extends JpaRepository<TblGroupEntity, String>{
	
	@Query("SELECT a FROM TblGroupEntity a WHERE a.departmentEntity.departmentCode = :departmentCode")
	List<TblGroupEntity> findByDepartmentCode(String departmentCode);
	

}
