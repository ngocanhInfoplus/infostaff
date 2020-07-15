package infostaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblStaffEntity;

@Repository
public interface TblStaffRepository extends JpaRepository<TblStaffEntity, Long> {
	
	@Query("SELECT a FROM TblStaffEntity a WHERE a.userName = :username AND a.recordStatus = :recordStatus")
	TblStaffEntity findActivedStaff(@Param("username") String username, @Param("recordStatus") String recordStatus);

}
