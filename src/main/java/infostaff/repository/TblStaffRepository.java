package infostaff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblStaffEntity;

@Repository
public interface TblStaffRepository extends JpaRepository<TblStaffEntity, Long> {

	@Query("SELECT a FROM TblStaffEntity a WHERE a.userName = :username AND a.recordStatus = :recordStatus")
	TblStaffEntity findActivedStaff(@Param("username") String username, @Param("recordStatus") String recordStatus);

	@Query("SELECT a FROM TblStaffEntity a" + "LEFT JOIN TblStaffEntity b "
			+ "ON a.staffId = b.managerId AND a.recordStatus = b.recordStatus "
			+ "WHERE b.staffId = :staffId AND a.recordStatus = :recordStatus")
	TblStaffEntity findManagerByStaffId(@Param("staffId") Long staffId, @Param("recordStatus") String recordStatus);
	
	@Query("SELECT a FROM TblStaffEntity a LEFT TblStaffGroupEntity b "
			+ "ON a.staffId = b.staffId AND a.recordStatus = b.recordStatus "
			+ "WHERE b.groupId = :groupId AND a.recordStatus = :recordStatus")
	List<TblStaffEntity> findStaffsByGroupId(@Param("groupId") Long groupId, @Param("recordStatus") String recordStatus);
}
