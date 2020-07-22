package infostaff.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblStaffTimeCardEntity;

@Repository
public interface TblStaffTimeCardRepository extends JpaRepository<TblStaffTimeCardEntity, Long> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE TblStaffTimeCardEntity a SET a.checkOut = :checkOut "
			+ "WHERE a.id = :id "
			+ "AND a.tblStaffEntity.staffId = :staffId "
			+ "AND workingDate = :workingDate")
	void updateTimeCard(@Param("id") Long id, @Param("checkOut") String checkOut, @Param("staffId") Long staffId,
			@Param("workingDate") Date workingDate);

	@Query("SELECT a FROM TblStaffTimeCardEntity a "
			+ "WHERE a.tblStaffEntity.staffId = :staffId "
			+ "AND a.isChecked = :isChecked "
			+ "AND a.workingDate = :workingDate "
			+ "AND a.recordStatus = :recordStatus")
	TblStaffTimeCardEntity findStaffDailyChecking(@Param("staffId") Long staffId,
			@Param("isChecked") boolean isChecked, @Param("workingDate") Date workingDate,
			@Param("recordStatus") String recordStatus);
	
	@Query("SELECT a FROM TblStaffTimeCardEntity a "
			+ "WHERE a.tblStaffEntity.staffId = :staffId "
			+ "AND a.workingDate BETWEEN :fromDate AND :toDate "
			+ "AND a.recordStatus = :recordStatus")
	List<TblStaffTimeCardEntity> findTimeCardByStaff(@Param("staffId") Long staffId,
			@Param("fromDate") Date fromDate, @Param("toDate") Date toDate, 
			@Param("recordStatus") String recordStatus);
	
	@Query("SELECT a FROM TblStaffTimeCardEntity a "
			+ "INNER JOIN TblStaffGroupEntity b "
			+ "ON (a.tblStaffEntity.staffId = b.staffId) "
			+ "WHERE b.groupId = :groupId AND b.recordStatus = 'O'"
			+ "AND a.workingDate BETWEEN :fromDate AND :toDate "
			+ "AND a.recordStatus = :recordStatus")
	List<TblStaffTimeCardEntity> findTimeCardByManagerAndGroupId(@Param("groupId") Long groupId,
			@Param("fromDate") Date fromDate, @Param("toDate") Date toDate, 
			@Param("recordStatus") String recordStatus);
	
	@Query("SELECT a FROM TblStaffTimeCardEntity a "
			+ "WHERE a.workingDate BETWEEN :fromDate AND :toDate "
			+ "AND a.recordStatus = :recordStatus")
	List<TblStaffTimeCardEntity> findTimeCardByManager(@Param("fromDate") Date fromDate, 
			@Param("toDate") Date toDate, 
			@Param("recordStatus") String recordStatus);
}
