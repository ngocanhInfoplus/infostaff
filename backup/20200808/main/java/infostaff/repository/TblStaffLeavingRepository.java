package infostaff.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblStaffLeavingEntity;

@Repository
public interface TblStaffLeavingRepository extends JpaRepository<TblStaffLeavingEntity, Long> {

	@Query("SELECT a FROM TblStaffLeavingEntity a WHERE a.tblStaffEntity.staffId = :staffId "
			+ "AND a.fromDate BETWEEN :fromDate AND :toDate " + "AND a.recordStatus = :recordStatus "
			+ "ORDER BY a.createdDate")
	List<TblStaffLeavingEntity> findActiveRequestByStaffIdAndDate(Long staffId, Date fromDate, Date toDate,
			String recordStatus);

	@Query("SELECT a FROM TblStaffLeavingEntity a WHERE a.tblStaffEntity.staffId = :staffId "
			+ "AND a.fromDate BETWEEN :fromDate AND :toDate " + "AND a.approveStatus = :approveStatus "
			+ "AND a.recordStatus = :recordStatus " + "ORDER BY a.createdDate")
	List<TblStaffLeavingEntity> findActiveRequestByStaffIdAndDateAndApproveStatus(Long staffId, String approveStatus,
			Date fromDate, Date toDate, String recordStatus);

	@Query("SELECT a FROM TblStaffLeavingEntity a WHERE a.fromDate BETWEEN :fromDate AND :toDate "
			+ "AND (a.managerId01 = :managerId OR a.managerId02 = :managerId) "
			+ "AND a.approveStatus = :approveStatus " 
			+ "AND a.recordStatus = :recordStatus "
			+ "ORDER BY a.createdDate")
	List<TblStaffLeavingEntity> findActiveRequestByManagerAndDateAndApproveStatus(Long managerId, String approveStatus,
			Date fromDate, Date toDate, String recordStatus);

	@Query("SELECT a FROM TblStaffLeavingEntity a WHERE a.fromDate BETWEEN :fromDate AND :toDate "
			+ "AND (a.managerId01 = :managerId OR a.managerId02 = :managerId) " 
			+ "AND a.approveStatus NOT IN ('I') "
			+ "AND a.recordStatus = :recordStatus "
			+ "ORDER BY a.createdDate")
	List<TblStaffLeavingEntity> findActiveRequestByManagerAndDate(Long managerId, Date fromDate, Date toDate,
			String recordStatus);

	@Query("SELECT a FROM TblStaffLeavingEntity a WHERE a.id = :id AND a.approveStatus = :approveStatus "
			+ "ORDER BY a.createdDate")
	TblStaffLeavingEntity findByIdAndApproveStatus(Long id, String approveStatus);

	@Query("SELECT SUM(a.totalDay) FROM TblStaffLeavingEntity a WHERE a.tblStaffEntity.staffId = :staffId "
			+ "AND a.fromDate BETWEEN :fromDate AND :toDate " 
			+ "AND a.approveStatus IN ('A', 'D') "
			+ "AND a.tblLeavingTypeEntity.leavingCode = 'ANNUAL' "
			+ "AND a.recordStatus = :recordStatus")
	Float getUsedAnnualLeaveDay(Long staffId, Date fromDate, Date toDate, String recordStatus);

}
