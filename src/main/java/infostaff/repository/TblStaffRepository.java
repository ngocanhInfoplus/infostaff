package infostaff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblStaffEntity;

@Repository
public interface TblStaffRepository extends JpaRepository<TblStaffEntity, Long> {

	@Query("SELECT a FROM TblStaffEntity a WHERE a.userName = :userName AND a.recordStatus = :recordStatus")
	TblStaffEntity findActivedStaff(@Param("userName") String staffName, @Param("recordStatus") String recordStatus);
	
	@Query("SELECT a FROM TblStaffEntity a WHERE a.staffId = :staffId AND a.recordStatus = :recordStatus")
	TblStaffEntity findByStaffId(@Param("staffId") Long staffId, @Param("recordStatus") String recordStatus);

	@Query("SELECT a FROM TblStaffEntity a LEFT JOIN TblStaffEntity b "
			+ "ON a.staffId = b.managerId AND a.recordStatus = b.recordStatus "
			+ "WHERE b.staffId = :staffId AND a.recordStatus = :recordStatus")
	TblStaffEntity findManagerByStaffId(@Param("staffId") Long staffId, @Param("recordStatus") String recordStatus);
	
	@Query("SELECT a FROM TblStaffEntity a LEFT JOIN TblStaffEntity b "
			+ "ON a.staffId = b.managerId AND a.recordStatus = b.recordStatus "
			+ "WHERE b.userName = :userName AND a.recordStatus = :recordStatus")
	List<TblStaffEntity> findManagerByUserName(@Param("userName") String userName, @Param("recordStatus") String recordStatus);
	
	@Query("SELECT a FROM TblStaffEntity a "
			+ "WHERE (a.managerId is null OR a.managerId = '') AND a.recordStatus = :recordStatus")
	List<TblStaffEntity> findAllManager(@Param("recordStatus") String recordStatus);
	
	@Query("SELECT a FROM TblStaffEntity a "
			+ "WHERE a.groupEntity.groupCode = :groupCode AND a.recordStatus = :recordStatus AND a.managerId IS NOT NULL")
	List<TblStaffEntity> findStaffsByGroupCode(@Param("groupCode") String groupCode, @Param("recordStatus") String recordStatus);
	
	@Query("SELECT a FROM TblStaffEntity a WHERE a.staffId IN (:staffIds) AND a.recordStatus = :recordStatus")
	List<TblStaffEntity> findManagers(@Param("staffIds") String staffIds, @Param("recordStatus") String recordStatus);
	
	@Query("SELECT a FROM TblStaffEntity a WHERE a.recordStatus = :recordStatus "
			 + "AND (:locationCode is null OR a.locationEntity.code = :locationCode) "
			 + "AND (:departmentCode is null OR a.groupEntity.departmentEntity.departmentCode = :departmentCode) "
			 + "AND (:countryCode is null OR a.countryEntity.code = :countryCode) "
			 + "AND (:jobTypeCode is null OR a.jobTitleEntity.code = :jobTypeCode) ")
	List<TblStaffEntity> findStaffs(@Param("locationCode") String locationCode
			, @Param("departmentCode") String departmentCode
			, @Param("countryCode") String countryCode
			, @Param("jobTypeCode") String jobTypeCode
			, @Param("recordStatus") String recordStatus);
	
}
