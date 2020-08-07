package infostaff.repository; 

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblStaffOvertimeEntity; 


//NOTE: Please change the JpaRepository entity type, defaule is String 
@Repository 
public interface TblStaffOvertimeRepository extends JpaRepository<TblStaffOvertimeEntity, Long> { 
	
	@Query("SELECT a FROM TblStaffOvertimeEntity a WHERE a.tblStaffEntity.staffId = :staffId "
			+ "AND a.registerDate BETWEEN :fromDate AND :toDate " + "AND a.recordStatus = :recordStatus "
			+ "ORDER BY a.createdDate")
	List<TblStaffOvertimeEntity> findByStaff(Long staffId, Date fromDate, Date toDate,
			String recordStatus);
	
	@Query("SELECT a FROM TblStaffOvertimeEntity a WHERE a.tblStaffEntity.staffId = :staffId "
			+ "AND a.registerDate BETWEEN :fromDate AND :toDate " + "AND a.approveStatus = :approveStatus "
			+ "AND a.recordStatus = :recordStatus " + "ORDER BY a.createdDate")
	List<TblStaffOvertimeEntity> findByStaff(Long staffId, String approveStatus,
			Date fromDate, Date toDate, String recordStatus);
	
	@Query("SELECT a FROM TblStaffOvertimeEntity a WHERE a.registerDate BETWEEN :fromDate AND :toDate "
			+ "AND (a.managerId01 = :managerId OR a.managerId02 = :managerId) "
			+ "AND a.approveStatus = :approveStatus " 
			+ "AND a.recordStatus = :recordStatus "
			+ "ORDER BY a.createdDate")
	List<TblStaffOvertimeEntity> findByManager(Long managerId, String approveStatus,
			Date fromDate, Date toDate, String recordStatus);

	@Query("SELECT a FROM TblStaffOvertimeEntity a WHERE a.registerDate BETWEEN :fromDate AND :toDate "
			+ "AND (a.managerId01 = :managerId OR a.managerId02 = :managerId) " 
			+ "AND a.approveStatus NOT IN ('I') "
			+ "AND a.recordStatus = :recordStatus "
			+ "ORDER BY a.createdDate")
	List<TblStaffOvertimeEntity> findByManager(Long managerId, Date fromDate, Date toDate,
			String recordStatus);
} 
